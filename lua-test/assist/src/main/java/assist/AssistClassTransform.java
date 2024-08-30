package assist;


import javassist.CtBehavior;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * 包装类，可以通过字节码增加
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-29 17:16
 */
public class AssistClassTransform implements ClassFileTransformer {

    private final JavaAssistBox javaAssistBox = JavaAssistBox.of();

    public AssistClassTransform() {
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classFileBuffer) {
        if ("java.lang.System".equalsIgnoreCase(className)) {
            final String finalClassName = className.replace("/", ".");
            JavaAssistBox.JavaAssist javaAssist = javaAssistBox.editClass(finalClassName);
            CtBehavior[] behaviors = javaAssist.getCtClass().getDeclaredBehaviors();

        }
        return classFileBuffer;
    }

}
