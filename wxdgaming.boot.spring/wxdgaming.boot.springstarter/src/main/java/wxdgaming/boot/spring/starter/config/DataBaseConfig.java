package wxdgaming.boot.spring.starter.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import wxdgaming.boot.batis.DbConfig;
import wxdgaming.boot.spring.starter.IBaseOrder;

/**
 * 数据库配置
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-27 10:47
 **/
@Slf4j
@Data
@Component(value = "0002")
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
