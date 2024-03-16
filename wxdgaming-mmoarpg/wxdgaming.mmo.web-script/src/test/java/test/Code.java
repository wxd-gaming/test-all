package test;

import org.junit.Test;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.core.timer.ann.Scheduled;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-10-16 11:10
 **/
public class Code {

    @Test
    public void c1() {
        ReflectContext context = ReflectContext.Builder.of("test").build();
        context.classWithSuper(I1.class).forEach(c -> System.out.println(c));
        System.out.println("====================================================================");
        context.stream().forEach(c -> c.methodStream().forEach(method -> System.out.println(method.getDeclaringClass() + " " + method)));
        System.out.println("====================================================================");
        context.stream().forEach(c -> c.methodsWithAnnotated(Test.class).forEach(method -> System.out.println(method.getDeclaringClass() + " " + method)));
        System.out.println("====================================================================");
        context.stream().forEach(c -> c.methodsWithAnnotated(Scheduled.class).forEach(method -> System.out.println(method.getDeclaringClass() + " " + method)));
    }

    @Scheduled
    public void t1() {}

    public static interface I1 {

        @Scheduled
        default void t1() {}

    }

    public static class C1 implements I1 {

    }

    public static class C2 implements I1 {

    }

}
