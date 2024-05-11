package wxdgaming.mmo.gamesr.cache.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.agent.function.Consumer2;
import wxdgaming.boot.agent.function.Function1;
import wxdgaming.boot.agent.function.Function2;
import wxdgaming.boot.core.lang.Cache;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService;
import wxdgaming.boot.starter.batis.RedisService;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.gamesr.bean.user.Player;

import java.util.concurrent.TimeUnit;

/**
 * 玩家角色快照
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 15:12
 **/
@Slf4j
@Singleton
public class PlayerCache extends Cache<Long, Player> implements IBeanInit {

    @Getter private static PlayerCache instance = null;

    @Inject MysqlService gameDb;
    @Inject RedisService redisService;

    public PlayerCache() {
        super(TimeUnit.MINUTES.toMillis(1));
        expireAfterAccess = (TimeUnit.MINUTES.toMillis(20));
        this.loader = new Function1<Long, Player>() {
            @Override public Player apply(Long aLong) {
                Player player = gameDb.queryEntity(Player.class, aLong);
                if (player == null) {
                    log.info("数据库查询失败：{}", aLong);
                } else {
                    if (cacheSize() > 50) {
                        log.info("当前缓存数量：{}", cacheSize());
                    } else if (cacheSize() > 200) {
                        log.info("当前缓存数量：{}", cacheSize(), new RuntimeException("路由跟踪"));
                    }
                }
                return player;
            }
        };

        heartTime = TimeUnit.MINUTES.toMillis(1);
        this.heartListener = new Consumer2<Long, Player>() {
            @Override public void accept(Long aLong, Player player) {
                if (player.checkSaveCode()) {
                    gameDb.getBatchPool().replace(player);
                }
            }
        };

        removalListener = new Function2<Long, Player, Boolean>() {
            @Override public Boolean apply(Long aLong, Player player) {
                gameDb.getBatchPool().replace(player);
                log.info("缓存过期移除：{}", player);
                return true;
            }
        };

    }

    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
    }

    public long accountDbSize() {
        return gameDb.rowCount(Player.class);
    }

    @Override public void put(Long aLong, Player player) {
        super.put(aLong, player);
        gameDb.getBatchPool().replace(player);
    }

    @Override public void putIfAbsent(Long aLong, Player player) {
        super.putIfAbsent(aLong, player);
        gameDb.getBatchPool().replace(player);
    }

}
