package code;

import org.junit.Test;

import java.lang.reflect.Method;

public class MethedTest {

    @Test
    public void t0() throws Exception {
        Class<System> systemClass = System.class;
        Method currentTimeMillis = systemClass.getMethod("currentTimeMillis");
        Object invoke = currentTimeMillis.invoke(null);
        System.out.println(invoke);
        System.out.println(System.currentTimeMillis());
        // JavaAssistBox.JavaAssist javaAssist = JavaAssistBox.of().editClass(System.class);
        // javaAssist
        //         .declaredMethod("currentTimeMillis", new Class[0], (ctMethod) -> {
        //             ctMethod.setBody("{return 0;}");
        //         })
        //         .createMethod("public static long millis() {return 2;}");
        // javaAssist.writeFile("target/out");
        // Class<?> aClass = javaAssist.loadClass();
        // System.out.println(aClass.getDeclaredMethod("millis").invoke(null));
    }
}
