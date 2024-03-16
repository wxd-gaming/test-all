package wxdgaming.mmo.gamesr.cache.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.agent.function.ConsumerE2;
import wxdgaming.boot.core.cache.CachePack;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService;
import wxdgaming.boot.starter.batis.RedisService;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.gamesr.bean.user.Player;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 玩家角色快照
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 15:12
 **/
@Slf4j
@Singleton
public class PlayerCache extends CachePack<Long, Player> implements IBeanInit {

    @Getter private static PlayerCache instance = null;

    @Inject MysqlService gameDb;
    @Inject RedisService redisService;

    public PlayerCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        this.loading = new Function<>() {
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

        this.heart = new Function<>() {

            @Override public Boolean apply(Player player) {
                if (player.checkSaveCode()) {
                    gameDb.getBatchPool().replace(player);
                }
                return !player.online();
            }

        };

        this.unload = new ConsumerE2<>() {
            @Override public void accept(Player player, String s) throws Exception {
                gameDb.getBatchPool().replace(player);
                log.info("缓存过期移除：{}, {}", player, s);
            }
        };

    }

    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
    }

    public long accountDbSize() {
        return gameDb.rowCount(Player.class);
    }

    @Override public void addCache(Long aLong, Player player) {
        super.addCache(aLong, player);
        gameDb.getBatchPool().replace(player);
    }
}
