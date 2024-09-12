package monitor;

import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

            if (!MonitorAgent.monitorConfig.getAgentArgs().isEmpty()
                    && MonitorAgent.monitorConfig.getAgentArgs().stream().noneMatch(finalClassName::startsWith)) {
                // System.out.println(finalClassName);
                return classfileBuffer;
            }

            if (!MonitorAgent.monitorConfig.getIgnoreArgs().isEmpty()
                    && MonitorAgent.monitorConfig.getIgnoreArgs().stream().anyMatch(finalClassName::startsWith)) {
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

            write("monitor load - " + finalClassName + " - method len " + declaredBehaviors.length);
            int len = 0;
            for (CtBehavior declaredBehavior : declaredBehaviors) {
                try {
                    if (declaredBehavior.isEmpty()) {
                        /*空方法，没意义*/
                        write("monitor load - " + finalClassName + " - 添加监控方法：" + declaredBehavior.getName() + " - 空方法");
                        continue;
                    }

                    String methodName = String.format(
                            "%s.%s#%s(%s)",
                            packName,
                            ctClass.getSimpleName(),
                            declaredBehavior.getName(),
                            Arrays.stream(declaredBehavior.getParameterTypes()).map(CtClass::getSimpleName).collect(Collectors.joining(", "))
                    );

                    if (!MonitorAgent.monitorConfig.getIgnoreArgs().isEmpty()
                            && MonitorAgent.monitorConfig.getIgnoreArgs().stream().anyMatch(methodName::startsWith)) {
                        continue;
                    }

                    CtClass longClass = pool.get(long.class.getName());
                    declaredBehavior.addLocalVariable("__TimeStart", longClass);
                    declaredBehavior.insertBefore("__TimeStart = java.lang.System.nanoTime();");
                    declaredBehavior.insertAfter(String.format(
                            "monitor.MonitorRecord.add(\"%s\", __TimeStart);",
                            methodName
                    ));
                    len++;
                    write("monitor load - " + finalClassName + " - 添加监控方法：" + methodName);
                } catch (Throwable e) {
                    write("monitor load - " + finalClassName + " - 添加监控方法：" + declaredBehavior.getName() + " - 异常：" + e.toString());
                }
            }
            if (len > 0) {
                if (MonitorAgent.monitorConfig.getOutClass().get()) {
                    ctClass.writeFile(MonitorAgent.monitorConfig.getOutPath() + "/classes");
                }
            }
            return ctClass.toBytecode();
        } catch (Throwable e) {
            write("monitor load - " + className + " - 异常：" + e.toString());
        }

        return classfileBuffer;
    }

    /** 覆盖 */
    public static void write(String content) {
        if (!MonitorAgent.monitorConfig.getOutInitLog().get()) return;
        String first = MonitorAgent.monitorConfig.getOutPath() + "/init.log";
        try {
            Path path = Paths.get(first);
            File parentFile = path.toFile().getParentFile();
            if (parentFile != null)
                parentFile.mkdirs();

            Files.write(
                    path,
                    (content + "\n").getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

        } catch (Exception e) {
            throw new RuntimeException(first, e);
        }
    }

}
