package wxdgaming.boot.spring.batis;

import wxdgaming.boot.batis.DbConfig;
import wxdgaming.boot.batis.redis.RedisDataHelper;

/**
 * redis
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-12-11 18:19
 **/
public class RedisService1 extends RedisDataHelper {

    public RedisService1(DbConfig dbConfig) {
        super(dbConfig);
    }

}
