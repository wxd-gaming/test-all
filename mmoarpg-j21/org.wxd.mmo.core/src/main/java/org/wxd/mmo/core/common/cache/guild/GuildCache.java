package org.wxd.mmo.core.common.cache.guild;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.agent.function.ConsumerE2;
import org.wxd.boot.core.cache.CachePack;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.batis.MysqlService;
import org.wxd.boot.starter.batis.RedisService;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.bean.guild.GuildInfo;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 账号缓存容器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 14:38
 **/
@Slf4j
@Singleton
public class GuildCache extends CachePack<Long, GuildInfo> implements IBeanInit {

    @Getter private static GuildCache instance = null;

    @Inject MysqlService gameDb;
    @Inject RedisService redisService;

    public GuildCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        loading = new Function<Long, GuildInfo>() {

            @Override public GuildInfo apply(Long aLong) {
                GuildInfo guildInfo = gameDb.queryEntity(GuildInfo.class, aLong);
                if (guildInfo == null) {
                    log.info("从数据库读取失败：{}", aLong);
                }
                return guildInfo;
            }

        };

        unload = new ConsumerE2<GuildInfo, String>() {
            @Override public void accept(GuildInfo guildInfo, String s) throws Exception {

            }
        };

        heart = new Function<GuildInfo, Boolean>() {

            @Override public Boolean apply(GuildInfo guildInfo) {

                return null;
            }

        };

    }

    /**
     * 初始化
     *
     * @param iocContext
     * @throws Exception
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-02-22 21:07
     */
    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
        gameDb.createTable(GuildInfo.class);
    }

    public long accountDbSize() {
        return gameDb.rowCount(GuildInfo.class);
    }

    @Override public void addCache(Long aLong, GuildInfo guildInfo) {
        super.addCache(aLong, guildInfo);
        gameDb.getBatchPool().replace(guildInfo);
    }

}
