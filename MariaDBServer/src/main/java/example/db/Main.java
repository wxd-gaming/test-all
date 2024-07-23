package example.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

    public static final File ok = new File("db-ok.txt");
    static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        log.info("s");
        DBFactory.getIns().init();

        System.out.println("====================================================");
        System.out.println("");
        System.out.println("数据库服务器启动完成  - " + "127.0.0.1:" + DBFactory.getIns().getMyDB().props.getProperty("port") + "/" + DBFactory.getIns().getMyDB().props.getProperty("database"));
        System.out.println("");
        System.out.println("====================================================");
        System.out.println("敲回车键可以关闭服务");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ok.delete();
            } catch (Exception ignore) {}
        }));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();
        DBFactory.getIns().getMyDB().stop();
        System.out.println("停止服务 成功");
        int i = 3;
        do {
            Thread.sleep(1000);
            System.out.println("即将退出：" + String.valueOf(i));
        } while (--i > 0);
        Runtime.getRuntime().exit(0);
    }

}