package org.wxd.mmo.gamesr.cache.mail;

import lombok.extern.slf4j.Slf4j;
import org.wxd.agent.function.ConsumerE2;
import org.wxd.boot.batis.mongodb.MongoDataHelper;
import org.wxd.boot.batis.redis.RedisDataHelper;
import org.wxd.boot.cache.CachePack;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IBeanInit;
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
@Resource
public class MailPackageCache extends CachePack<Long, MailPackage> implements IBeanInit {

    private static MailPackageCache instance = null;

    public static MailPackageCache getInstance() {
        return instance;
    }

    @Resource MongoDataHelper mongoDataHelper;
    @Resource RedisDataHelper redisDataHelper;

    public MailPackageCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        this.loading = new Function<>() {
            @Override public MailPackage apply(Long aLong) {
                MailPackage mailPackage = mongoDataHelper.queryEntity(MailPackage.class, aLong);
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
                if (mailPackage.checkSaveCode()) {
                    mongoDataHelper.getBatchPool().replace(mailPackage);
                }
                return null;
            }
        };

        this.unload = new ConsumerE2<>() {
            @Override public void accept(MailPackage mailPackage, String s) throws Exception {
                mongoDataHelper.getBatchPool().replace(mailPackage);
                log.info("缓存过期移除：{}, {}", mailPackage, s);
            }
        };

    }

    @Override public void beanInit(IocInjector iocInjector) throws Exception {
        instance = this;
    }

    public long accountDbSize() {
        return mongoDataHelper.estimatedDocumentCount(Player.class);
    }

    @Override public void addCache(Long aLong, MailPackage mailPackage) {
        super.addCache(aLong, mailPackage);
        mongoDataHelper.getBatchPool().replace(mailPackage);
    }

}