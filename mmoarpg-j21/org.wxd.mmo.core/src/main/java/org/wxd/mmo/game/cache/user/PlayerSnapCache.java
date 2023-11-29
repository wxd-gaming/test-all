package org.wxd.mmo.game.cache.user;

import org.wxd.agent.function.ConsumerE2;
import org.wxd.boot.batis.mongodb.MongoDataHelper;
import org.wxd.boot.batis.redis.RedisDataHelper;
import org.wxd.boot.cache.CachePack;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IBeanInit;
import org.wxd.mmo.game.bean.user.PlayerSnap;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 玩家角色快照
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 14:54
 **/
@Resource
public class PlayerSnapCache extends CachePack<Long, PlayerSnap> implements IBeanInit {

    private static PlayerSnapCache instance = null;

    public static PlayerSnapCache getInstance() {
        return instance;
    }

    @Resource MongoDataHelper mongoDataHelper;
    @Resource RedisDataHelper redisDataHelper;

    public PlayerSnapCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        loading = new Function<Long, PlayerSnap>() {

            @Override public PlayerSnap apply(Long aLong) {

                return null;
            }

        };

        unload = new ConsumerE2<PlayerSnap, String>() {
            @Override public void accept(PlayerSnap playerSnap, String s) throws Exception {

            }
        };

        heart = new Function<PlayerSnap, Boolean>() {

            @Override public Boolean apply(PlayerSnap playerSnap) {

                return null;
            }

        };

    }

    @Override public void beanInit(IocInjector iocInjector) throws Exception {
        instance = this;
    }

    public long accountDbSize() {
        return mongoDataHelper.estimatedDocumentCount(PlayerSnap.class);
    }

    @Override public void addCache(Long aLong, PlayerSnap playerSnap) {
        super.addCache(aLong, playerSnap);
        mongoDataHelper.getBatchPool().replace(playerSnap);
    }

}
