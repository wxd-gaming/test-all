package wxdgaming.mmo.core.game.cache.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import wxdgaming.boot.agent.function.Consumer2;
import wxdgaming.boot.agent.function.Function1;
import wxdgaming.boot.agent.function.Function2;
import wxdgaming.boot.core.lang.Cache;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService1;
import wxdgaming.boot.starter.batis.RedisService;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.core.game.bean.user.PlayerSnap;

import java.util.concurrent.TimeUnit;

/**
 * 玩家角色快照
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 14:54
 **/
@Singleton
public class PlayerSnapCache extends Cache<Long, PlayerSnap> implements IBeanInit {

    @Getter private static PlayerSnapCache instance = null;

    @Inject MysqlService1 loginDb;
    @Inject RedisService redisService;

    public PlayerSnapCache() {
        super(TimeUnit.MINUTES.toMillis(1));
        expireAfterAccess = (TimeUnit.MINUTES.toMillis(20));


        loader = new Function1<Long, PlayerSnap>() {
            @Override public PlayerSnap apply(Long aLong) {

                return null;
            }
        };

        removalListener = new Function2<Long, PlayerSnap, Boolean>() {
            @Override public Boolean apply(Long aLong, PlayerSnap playerSnap) {
                return null;
            }
        };

        heartTime = (TimeUnit.MINUTES.toMillis(1));
        heartListener = new Consumer2<Long, PlayerSnap>() {
            @Override public void accept(Long aLong, PlayerSnap playerSnap) {

            }
        };

    }

    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
        loginDb.createTable(PlayerSnap.class);
    }

    public long accountDbSize() {
        return loginDb.rowCount(PlayerSnap.class);
    }

    @Override public void put(Long aLong, PlayerSnap playerSnap) {
        super.put(aLong, playerSnap);
        loginDb.getBatchPool().replace(playerSnap);
    }

    @Override public void putIfAbsent(Long aLong, PlayerSnap playerSnap) {
        super.putIfAbsent(aLong, playerSnap);
        loginDb.getBatchPool().replace(playerSnap);
    }


}
