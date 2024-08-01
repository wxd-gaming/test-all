package wxdgaming.mmo.core.common.cache.guild;

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
import wxdgaming.mmo.core.bean.guild.GuildInfo;

import java.util.concurrent.TimeUnit;

/**
 * 账号缓存容器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-03 14:38
 **/
@Slf4j
@Singleton
public class GuildCache extends Cache<Long, GuildInfo> implements IBeanInit {

    @Getter private static GuildCache instance = null;

    @Inject MysqlService gameDb;
    @Inject RedisService redisService;

    public GuildCache() {
        super(TimeUnit.MINUTES.toMillis(1));

        expireAfterAccess = (TimeUnit.MINUTES.toMillis(20));

        loader = new Function1<Long, GuildInfo>() {
            @Override public GuildInfo apply(Long aLong) {
                GuildInfo guildInfo = gameDb.queryEntity(GuildInfo.class, aLong);
                if (guildInfo == null) {
                    log.info("从数据库读取失败：{}", aLong);
                }
                return guildInfo;
            }
        };

        removalListener = new Function2<Long, GuildInfo, Boolean>() {

            @Override public Boolean apply(Long aLong, GuildInfo guildInfo) {

                return null;
            }

        };

        heartTime = (TimeUnit.MINUTES.toMillis(1));
        heartListener = new Consumer2<Long, GuildInfo>() {

            @Override public void accept(Long aLong, GuildInfo guildInfo) {

            }

        };

    }

    /**
     * 初始化
     *
     * @param iocContext
     * @throws Exception
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-02-22 21:07
     */
    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
        gameDb.createTable(GuildInfo.class);
    }

    public long accountDbSize() {
        return gameDb.rowCount(GuildInfo.class);
    }

    /**
     * 添加缓存
     *
     * @param aLong
     * @param guildInfo
     */
    @Override public void put(Long aLong, GuildInfo guildInfo) {
        super.put(aLong, guildInfo);
        gameDb.getBatchPool().replace(guildInfo);
    }

    /**
     * 添加缓存
     *
     * @param aLong
     * @param guildInfo
     */
    @Override public void putIfAbsent(Long aLong, GuildInfo guildInfo) {
        super.putIfAbsent(aLong, guildInfo);
        gameDb.getBatchPool().replace(guildInfo);
    }

}
