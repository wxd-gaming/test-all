package code;

import org.junit.Test;

/**
 * 参数传递测试
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-10-21 19:13
 **/
public class ArgsTest {

    @Test
    public void t0() {
        t00("fun", 2L, 3L);
    }

    public void t00(String fun, Object... args) {
        t01(fun, 1L, args);
    }

    public void t01(String fun, Object... args) {
        System.out.println(fun);
        System.out.println(args.length);
    }

}
