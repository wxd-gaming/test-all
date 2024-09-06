package agent.assist;

import agent.service.BaseTransformer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.bytecode.ClassFilePrinter;
import javassist.bytecode.MethodInfo;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AssistTransformer extends BaseTransformer {

    public AssistTransformer(String agentArgs, Instrumentation inst) {
        super(agentArgs, inst);
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if ("agent/assist/MyClass".equals(className)) {
            try {
                // 加载类池
                ClassPool pool = ClassPool.getDefault();
                pool.insertClassPath(new LoaderClassPath(loader));
                // 获取类
                CtClass ctClass = pool.get(className.replace("/", "."));
                // 获取方法
                CtMethod ctMethod = ctClass.getDeclaredMethod("doSomething");
                // 修改第 10 行代码
                ctMethod.insertAt(8, " c = 1 * 1;");
                // 输出修改后的类文件
                ctClass.writeFile("agent/assist/target/out");
                return ctClass.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return classfileBuffer;
    }
}
