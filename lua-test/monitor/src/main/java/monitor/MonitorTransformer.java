package monitor;

import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.LoaderClassPath;

import javax.annotation.Resource;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 监控
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-10 20:45
 **/
public class MonitorTransformer implements ClassFileTransformer {

    @Override public byte[] transform(ClassLoader loader,
                                      String className,
                                      Class<?> classBeingRedefined,
                                      ProtectionDomain protectionDomain,
                                      byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            if (className == null || className.isEmpty()) return classfileBuffer;
            String finalClassName = className.replace("/", ".");
            if (!MonitorAgent.monitorConfig.getIgnoreArgs().isEmpty()
                    && MonitorAgent.monitorConfig.getIgnoreArgs().stream().anyMatch(finalClassName::startsWith)) {
                // System.out.println(MonitorAgent.monitorConfig.getIgnoreArgs().size() + " - " + finalClassName);
                return classfileBuffer;
            }

            if (!MonitorAgent.monitorConfig.getAgentArgs().isEmpty()
                    && MonitorAgent.monitorConfig.getAgentArgs().stream().noneMatch(finalClassName::startsWith)) {
                // System.out.println(finalClassName);
                return classfileBuffer;
            }

            // 加载类池
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(new LoaderClassPath(loader));
            // 获取类
            CtClass ctClass = pool.get(finalClassName);
            String packName = Arrays.stream(ctClass.getPackageName().split("\\."))
                    .map(p -> p.substring(0, 1))
                    .collect(Collectors.joining("."));
            CtBehavior[] declaredBehaviors = ctClass.getDeclaredBehaviors();
            for (CtBehavior declaredBehavior : declaredBehaviors) {
                try {
                    if (declaredBehavior.hasAnnotation(Deprecated.class)) continue;
                    Resource annotation = (Resource) declaredBehavior.getAnnotation(Resource.class);
                    if (annotation != null && "ignore".equalsIgnoreCase(annotation.description())) {
                        continue;
                    }
                    if (declaredBehavior.isEmpty()) {
                        /*空方法，没意义*/
                        continue;
                    }
                    CtClass longClass = pool.get(long.class.getName());
                    declaredBehavior.addLocalVariable("__TimeStart", longClass);
                    declaredBehavior.insertBefore("__TimeStart = java.lang.System.nanoTime();");
                    declaredBehavior.insertAfter(String.format(
                            "monitor.MonitorRecord.add(\"%s.%s#%s(%s)\", __TimeStart);",
                            packName,
                            ctClass.getSimpleName(),
                            declaredBehavior.getName(),
                            Arrays.stream(declaredBehavior.getParameterTypes()).map(CtClass::getSimpleName).collect(Collectors.joining(", "))
                    ));
                } catch (Exception e) {
                    new RuntimeException(className + "#" + declaredBehavior.getLongName(), e).printStackTrace();
                }
            }
            String outPath = MonitorAgent.monitorConfig.getOutPath().get();
            if (outPath != null && !outPath.trim().isEmpty()) {
                ctClass.writeFile("target/out");
            }
            return ctClass.toBytecode();
        } catch (Exception e) {
            new RuntimeException(className, e).printStackTrace();
        }

        return classfileBuffer;
    }

}
