package code;

import org.junit.Test;

import java.util.Comparator;
import java.util.stream.Stream;

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

    @Test
    public void t02() {
        Stream.of("dd", "GAme", "_d", "zz", "a", "1", "1/3","1/3/e.e", "0", "A")
                .sorted(Comparator.comparing(String::toLowerCase))
                .forEach(System.out::println);
    }

}
