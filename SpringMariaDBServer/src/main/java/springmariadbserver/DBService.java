package springmariadbserver;

import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

/**
 * 数据库工厂
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-23 16:45
 **/
@Slf4j
@Getter
@Service
public class DBService {

    public DBService() {}

    public final File ok = new File("db-ok.txt");
    private DBConfigurationBuilder configBuilder;
    private MyDB myDB;

    @PostConstruct
    public void init() throws Exception {
        Properties props = new Properties();
        props.load(Files.newInputStream(Paths.get("my.ini")));

        configBuilder = DBConfigurationBuilder.newBuilder();
        configBuilder.setPort(Integer.parseInt(props.getProperty("port"))); // OR, default: setPort(0); => autom. detect free port
        configBuilder.setBaseDir("data-base/"); // just an example
        configBuilder.setDataDir("data-base/data");

        myDB = new MyDB(props, configBuilder.build());
        myDB.start();

        System.out.println("====================================================");
        System.out.println("");
        System.out.println("数据库服务器启动完成  - " + "127.0.0.1:" + myDB.props.getProperty("port") + "/" + myDB.props.getProperty("database"));
        System.out.println("");
        System.out.println("====================================================");

        Files.write(
                ok.toPath(),
                String.valueOf(fetchProcessId()).getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

    }

    // @Scheduled(cron = "0/15 * * * * ?")
    // public void timer() {
    //     log.info("\n{}\n", "等待程序关闭");
    // }

    public static int fetchProcessId() {
        try {
            RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
            String name = runtime.getName();
            return Integer.parseInt(name.substring(0, name.indexOf("@")));
        } catch (Exception ignore) {
            return -1;
        }
    }

    public void stop() {
        try {
            getOk().delete();
        } catch (Exception ignore) {}
        try {
            getMyDB().stop();
        } catch (Throwable e) {
            log.error("关闭服务", e);
        }
        for (int i = 3; i > 0; i--) {
            log.error("程序退出");
            System.out.println("exit " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {}
        }
    }

}
