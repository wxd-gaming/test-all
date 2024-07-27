package wxdgaming.boot.springstarter.config.db;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import wxdgaming.boot.batis.DbConfig;

/**
 * 数据库配置
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-27 10:47
 **/
@Slf4j
@Setter
@Getter
@Order(3)
@ConfigurationProperties(prefix = "database")
public class DataBaseConfig {

    private DbConfig mysql = null;
    private DbConfig mysql1 = null;
    private DbConfig mysql2 = null;
    private DbConfig mysql3 = null;

    private DbConfig redis = null;
    private DbConfig redis1 = null;
    private DbConfig redis2 = null;
    private DbConfig redis3 = null;

    public DataBaseConfig() {
        System.out.println("\n\n" + this.getClass() + "\n\n");
    }

    // /** 初始化 */
    // @PostConstruct
    // public void init() throws Exception {
    //     try {
    //         StringBuilder stringBuilder = new StringBuilder();
    //         if (mysql != null) {
    //             stringBuilder.append(String.format("%15s = %s", "mysql", mysql)).append("\n");
    //             MysqlDataHelper instance = new MysqlService(mysql);
    //             SpringContext.registerInstance(mysql.getName(), instance, false);
    //         }
    //
    //         if (mysql1 != null) {
    //             stringBuilder.append(String.format("%15s = %s", "mysql", mysql1)).append("\n");
    //             MysqlDataHelper instance = new MysqlService1(mysql1);
    //             SpringContext.registerInstance(mysql1.getName(), instance, false);
    //         }
    //
    //         if (stringBuilder.length() > 0) {
    //             log.info("\n\n{}", stringBuilder.toString());
    //         }
    //     } catch (Throwable t) {
    //         throw new RuntimeException(t);
    //     }
    // }

}
