package example.db;

import ch.vorburger.mariadb4j.DBConfigurationBuilder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 数据库工厂
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-23 16:45
 **/

public class DBFactory {

    private static final DBFactory ins = new DBFactory();

    public static DBFactory getIns() {
        return ins;
    }

    DBFactory() {}

    private DBConfigurationBuilder configBuilder;
    private MyDB myDB;

    public void init() throws Exception {
        Properties props = new Properties();
        props.load(Files.newInputStream(Paths.get("my.ini")));

        configBuilder = DBConfigurationBuilder.newBuilder();
        configBuilder.setPort(Integer.parseInt(props.getProperty("port"))); // OR, default: setPort(0); => autom. detect free port
        configBuilder.setBaseDir("data-base/"); // just an example
        configBuilder.setDataDir("data-base/data");

        myDB = new MyDB(props, configBuilder.build());
        myDB.start();
    }

    public DBConfigurationBuilder getConfigBuilder() {
        return configBuilder;
    }

    public MyDB getMyDB() {
        return myDB;
    }
}
