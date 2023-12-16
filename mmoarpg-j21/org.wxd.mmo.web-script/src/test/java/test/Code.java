package test;

import org.junit.Test;
import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.timer.ann.Scheduled;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-10-16 11:10
 **/
public class Code {

    @Test
    public void c1() {
        ReflectContext context = ReflectContext.Builder.of("test").build();
        context.getContentList().forEach(c -> c.methodStream().forEach(method -> System.out.println(method.getDeclaringClass() + " " + method)));
        System.out.println("====================================================================");
        context.getContentList().forEach(c -> c.methodsWithAnnotated(Test.class).forEach(method -> System.out.println(method.getDeclaringClass() + " " + method)));
        System.out.println("====================================================================");
        context.getContentList().forEach(c -> c.methodsWithAnnotated(Scheduled.class).forEach(method -> System.out.println(method.getDeclaringClass() + " " + method)));
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
