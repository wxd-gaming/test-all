package wxdgaming.boot.spring.starter.service.batis.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import wxdgaming.boot.batis.redis.RedisDataHelper;
import wxdgaming.boot.spring.starter.service.batis.DataBaseConfig;

/**
 * redis
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-12-11 18:19
 **/
@Component
@ConditionalOnProperty(value = "wxdgaming.database.redis2.dbHost")
public class RedisService2 extends RedisDataHelper {

    public RedisService2(DataBaseConfig dbConfig) {
        super(dbConfig.getRedis2());
    }

}
