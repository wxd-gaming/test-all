package org.wxd.mmo.gamesr.cache.mail;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.agent.function.ConsumerE2;
import org.wxd.boot.core.cache.CachePack;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.batis.MongoService1;
import org.wxd.boot.starter.batis.MysqlService;
import org.wxd.boot.starter.batis.RedisService;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.gamesr.bean.mail.MailPackage;
import org.wxd.mmo.gamesr.bean.user.Player;

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
public class MailPackageCache extends CachePack<Long, MailPackage> implements IBeanInit {

    @Getter private static MailPackageCache instance = null;

    @Inject MysqlService gameDb;
    @Inject RedisService redisService;

    public MailPackageCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        this.loading = new Function<>() {
            @Override public MailPackage apply(Long aLong) {
                MailPackage mailPackage = gameDb.queryEntity(MailPackage.class, aLong);
                if (mailPackage == null) {
                    log.info("数据库查询失败：{}", aLong);
                } else {
                    if (cacheSize() > 50) {
                        log.info("当前缓存数量：{}", cacheSize());
                    } else if (cacheSize() > 200) {
                        log.info("当前缓存数量：{}", cacheSize(), new RuntimeException("路由跟踪"));
                    }
                }
                return mailPackage;
            }
        };

        this.heart = new Function<>() {
            @Override public Boolean apply(MailPackage mailPackage) {
                if (mailPackage.checkSaveCode()) {/*判定异常是否需要存储*/
                    gameDb.getBatchPool().replace(mailPackage);
                }
                return null;
            }
        };

        this.unload = new ConsumerE2<>() {
            @Override public void accept(MailPackage mailPackage, String s) throws Exception {
                gameDb.getBatchPool().replace(mailPackage);
                log.info("缓存过期移除：{}, {}", mailPackage, s);
            }
        };

    }

    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
    }

    public long accountDbSize() {
        return gameDb.rowCount(Player.class);
    }

    @Override public void addCache(Long aLong, MailPackage mailPackage) {
        super.addCache(aLong, mailPackage);
        gameDb.getBatchPool().replace(mailPackage);
    }

}
