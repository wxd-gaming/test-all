package code.m;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-11 09:23
 **/
public class MonitorTest {


    public static void test() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] test(String[] args) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return args;
    }

    public static String[] test2(String[] args) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return args;
    }

    public MonitorTest() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    long start = System.currentTimeMillis();

    public void t3() {
        start = System.currentTimeMillis();
    }

}
