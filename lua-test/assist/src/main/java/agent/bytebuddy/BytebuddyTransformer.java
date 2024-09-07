package agent.bytebuddy;

import agent.service.BaseTransformer;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class BytebuddyTransformer extends BaseTransformer {

    public BytebuddyTransformer(String agentArgs, Instrumentation inst) {
        super(agentArgs, inst);
        try {
            new ByteBuddy()
                    .redefine(System.class)
                    .method(ElementMatchers.named("currentTimeMillis"))
                    .intercept(FixedValue.value(444L))
                    .make()
                    .load(Target.class.getClassLoader(), new ClassReloadingStrategy(inst, ClassReloadingStrategy.Strategy.RETRANSFORMATION));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {

        return classfileBuffer;
    }

}
