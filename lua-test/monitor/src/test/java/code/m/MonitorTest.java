package code.m;

import javax.annotation.Resource;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-11 09:23
 **/
public class MonitorTest {


    public static void test() {
        try {
            Thread.sleep(10);
            System.out.println("hello world");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] test(String[] args) {
        try {
            Thread.sleep(10);
            System.out.println("hello world");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return args;
    }

    @Resource(description = "ignore")
    public static String[] test2(String[] args) {
        try {
            Thread.sleep(10);
            System.out.println("hello world");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return args;
    }

    @Deprecated
    public MonitorTest() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void t3() {
        System.out.println("hello world");
    }

}
