package wxdgaming.boot.spring.a;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wxdgaming.boot.batis.DbConfig;

import java.io.Serializable;

/**
 * 数据库配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-27 10:47
 **/
@Slf4j
@Data
@Order(1)
@Component
@DependsOn(value = {"threadPoolConfig"})
@ConfigurationProperties(prefix = "database")
public class DataBaseConfig implements Serializable {

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
