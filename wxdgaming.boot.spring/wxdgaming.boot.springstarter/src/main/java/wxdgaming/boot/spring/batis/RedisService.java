package wxdgaming.boot.spring.batis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import wxdgaming.boot.batis.redis.RedisDataHelper;
import wxdgaming.boot.spring.starter.config.DataBaseConfig;

/**
 * redis
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-12-11 18:19
 **/
@Service
@ConditionalOnProperty(value = "database.redis.dbHost")
public class RedisService extends RedisDataHelper {

    @Autowired
    public RedisService(DataBaseConfig dbConfig) {
        super(dbConfig.getRedis());
    }

}
