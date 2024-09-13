package code;

import code.m.MonitorTest;
import monitor.MonitorConfig;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class TestMain {

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            while (true) {
                int nextInt = ThreadLocalRandom.current().nextInt(300, 3000);
                try {
                    Thread.sleep(nextInt);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < 30; i++) {
                    // long nanoTime = System.nanoTime();
                    MonitorTest.test();
                    MonitorTest.test(args);
                    MonitorTest.test2(args);
                    new MonitorTest().t3();
                    // MonitorRecord.add(MonitorTest.class, "main", nanoTime);
                }
                System.out.println("ok");

            }
        }).start();


        System.in.read();
    }

    @Test
    public void t0() {
        System.out.println(Arrays.asList("a", "b"));
        System.out.println(new HashSet<>(Arrays.asList("a", "b")));
    }

    @Test
    public void t2() {
        double number = 123456789.123456;
        DecimalFormat decimalFormat = new DecimalFormat("#.####"); // 根据需要定制格式
        String formattedNumber = decimalFormat.format(number);
        System.out.println(formattedNumber); // 输出: 123456789.123456
    }

    @Test
    public void t3() {
        MonitorConfig monitorConfig = MonitorConfig.create(null, "monitor.yml");
        System.out.println(monitorConfig);
    }

}
