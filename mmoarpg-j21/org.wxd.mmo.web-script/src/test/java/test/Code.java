package test;

import org.junit.Test;
import org.wxd.agent.system.ReflectBuilder;
import org.wxd.boot.timer.ann.Scheduled;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-10-16 11:10
 **/
public class Code {

    @Test
    public void c1() {
        ReflectBuilder.ReflectContext context = ReflectBuilder.of("test").build();
        context.methodStream().forEach(System.out::println);
        System.out.println("====================================================================");
        context.methodsWithAnnotated(Test.class).forEach(System.out::println);
        System.out.println("====================================================================");
        context.methodsWithAnnotated(Scheduled.class).forEach(System.out::println);
    }

    @Scheduled
    public void t1() {}

    public static interface I1 {

        @Scheduled
        default void t1() {}

    }

    public static class C1 implements I1 {

    }

}
