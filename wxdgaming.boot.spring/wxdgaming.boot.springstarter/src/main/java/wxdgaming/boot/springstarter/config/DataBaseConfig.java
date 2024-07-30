package wxdgaming.boot.springstarter.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import wxdgaming.boot.batis.DbConfig;
import wxdgaming.boot.springstarter.IBaseOrder;

/**
 * 数据库配置
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-27 10:47
 **/
@Slf4j
@Data
@Order(3)
@ConfigurationProperties(prefix = "database")
public class DataBaseConfig implements IBaseOrder {

    private DbConfig mysql = null;
    private DbConfig mysql1 = null;
    private DbConfig mysql2 = null;
    private DbConfig mysql3 = null;

    private DbConfig redis = null;
    private DbConfig redis1 = null;
    private DbConfig redis2 = null;
    private DbConfig redis3 = null;

    public DataBaseConfig() {
        System.out.println("\n" + this.getClass());
    }

}
