package code;

import code.m.MonitorTest;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class TestMain {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 30; i++) {
            // long nanoTime = System.nanoTime();
            MonitorTest.test();
            MonitorTest.test(args);
            MonitorTest.test2(args);
            new MonitorTest().t3();
            // MonitorRecord.add(MonitorTest.class, "main", nanoTime);
        }
        System.in.read();
    }

    @Test
    public void t0() {
        System.out.println(Arrays.asList("a", "b"));

        System.out.println(new HashSet<>(Arrays.asList("a", "b")));
    }

}
