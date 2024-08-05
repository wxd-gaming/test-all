package fxdb.fxdb.service;

import ch.vorburger.mariadb4j.DBConfigurationBuilder;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * 数据库工厂
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-23 16:45
 **/

public class DBFactory {

    private static final DBFactory ins = new DBFactory();
    public static final File ok = new File("db-ok.txt");

    public static DBFactory getIns() {
        return ins;
    }


    DBFactory() {}

    private DBConfigurationBuilder configBuilder;
    private MyDB myDB;

    public void init(String dataBase, int port, String user, String pwd) throws Exception {

        configBuilder = DBConfigurationBuilder.newBuilder();
        configBuilder.setPort(port); // OR, default: setPort(0); => autom. detect free port
        configBuilder.setBaseDir("data-base/"); // just an example
        configBuilder.setDataDir("data-base/data");

        myDB = new MyDB(configBuilder.build(), dataBase, user, pwd, 60);
        myDB.start();
        write(1);
    }

    public static void write(int state) throws IOException {
        String json = "{\"PID\":" + fetchProcessId() + ", \"states\":" + state + "}";
        Files.write(
                ok.toPath(),
                json.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    public DBConfigurationBuilder getConfigBuilder() {
        return configBuilder;
    }

    public MyDB getMyDB() {
        return myDB;
    }


    public void stop() {
        System.out.println("addShutdownHook");
        try {
            write(0);
        } catch (Exception ignore) {}
        try {
            if (getMyDB() != null) {
                getMyDB().stop();
            }
        } catch (Throwable e) {
            System.out.println("关闭服务 " + e);
        }
        System.out.println("关闭服务完成");
    }

    public static String fetchProcessId() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            String name = runtime.getName();
            return name.substring(0, name.indexOf("@"));

        } catch (Exception ignore) {
            return "-1";
        }
    }
}
