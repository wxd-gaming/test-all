package example.console;

import db.server.DBFactory;
import db.server.GraalvmUtil;
import db.server.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ConsoleMain {

    static Logger log = LoggerFactory.getLogger(ConsoleMain.class);

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            List<String> strings = GraalvmUtil.jarResources();
            for (String string : strings) {
                URL resource = contextClassLoader.getResource(string);
                System.out.println(string + " - " + resource);
            }
        }
        DBFactory.getIns().init();

        WebService.getIns().start();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("====================================================").append("\n");
        stringBuilder.append("").append("\n");
        stringBuilder.append("数据库服务器启动完成  - " + "127.0.0.1:" + DBFactory.getIns().getMyDB().getConfiguration().getPort()).append("\n");
        stringBuilder.append("敲回车键可以关闭服务").append("\n");
        stringBuilder.append("").append("\n");
        stringBuilder.append("====================================================").append("\n");
        log.info(stringBuilder.toString());
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                log.info("敲回车，等待程序退出");
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        thread.start();

        final Thread hook = new Thread(() -> {
            DBFactory.getIns().stop();
            Runtime.getRuntime().halt(0);
        });
        Runtime.getRuntime().addShutdownHook(hook);

        if (args.length > 0) {
            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(30_000);
                    hook.start();
                } catch (Exception ignore) {}
            });
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        try {
            thread.interrupt();
            thread.join();
        } catch (Exception ignore) {}

        hook.run();
    }


}