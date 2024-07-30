package wxdgaming.boot.spring.starter.service.batis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wxdgaming.boot.batis.DbConfig;
import wxdgaming.boot.batis.redis.RedisDataHelper;

/**
 * redis
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-12-11 18:19
 **/
@Order(201)
@Component
@ConditionalOnProperty(value = "database.redis1.dbHost")
public class RedisService1 extends RedisDataHelper {

    public RedisService1(DbConfig dbConfig) {
        super(dbConfig);
    }

}
