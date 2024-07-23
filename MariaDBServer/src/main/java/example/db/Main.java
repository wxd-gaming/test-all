package example.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class Main {

    public static final File ok = new File("db-ok.txt");
    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        log.info("s");
        DBFactory.getIns().init();

        System.out.println("====================================================");
        System.out.println("");
        System.out.println("数据库服务器启动完成  - " + "127.0.0.1:" + DBFactory.getIns().getMyDB().props.getProperty("port") + "/" + DBFactory.getIns().getMyDB().props.getProperty("database"));
        System.out.println("敲回车键可以关闭服务");
        System.out.println("");
        System.out.println("====================================================");
        Files.write(
                ok.toPath(),
                String.valueOf(fetchProcessId()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("敲回车，等待程序退出");
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ok.delete();
            } catch (Exception ignore) {}
        }));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        try {
            thread.interrupt();
            thread.join();
        } catch (Exception ignore) {}
        try {
            ok.delete();
        } catch (Exception ignore) {}
        DBFactory.getIns().getMyDB().stop();
        System.out.println("停止服务 成功");
        int i = 3;
        do {
            Thread.sleep(1000);
            System.out.println("即将退出：" + String.valueOf(i));
        } while (--i > 0);
        Runtime.getRuntime().exit(0);
    }

    public static final int fetchProcessId() {
        try {
            /*RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            Field jvm = runtime.getClass().getDeclaredField("jvm");
            jvm.setAccessible(true);
            VMManagement mgmt = (VMManagement) jvm.get(runtime);
            Method pidMethod = mgmt.getClass().getDeclaredMethod("getProcessId");
            pidMethod.setAccessible(true);
            int pid = (Integer) pidMethod.invoke(mgmt);
            return pid;
            */

            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            String name = runtime.getName();
            return Integer.parseInt(name.substring(0, name.indexOf("@")));

        } catch (Exception ignore) {
            return -1;
        }
    }
}