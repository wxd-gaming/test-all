package wxdgaming.mmo.gamesr.cache.mail;

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
import wxdgaming.mmo.gamesr.bean.mail.MailPackage;
import wxdgaming.mmo.gamesr.bean.user.Player;

import java.util.concurrent.TimeUnit;

/**
 * 玩家角色快照
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-03 15:12
 **/
@Slf4j
@Singleton
public class MailPackageCache extends Cache<Long, MailPackage> implements IBeanInit {

    @Getter private static MailPackageCache instance = null;

    @Inject MysqlService gameDb;
    @Inject RedisService redisService;

    public MailPackageCache() {
        super(TimeUnit.MINUTES.toMillis(1));
        expireAfterAccess = (TimeUnit.MINUTES.toMillis(20));
        this.loader = new Function1<Long, MailPackage>() {
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

        heartTime = TimeUnit.MINUTES.toMillis(1);
        this.heartListener = new Consumer2<Long, MailPackage>() {
            @Override public void accept(Long aLong, MailPackage mailPackage) {
                if (mailPackage.checkSaveCode()) {/*判定异常是否需要存储*/
                    gameDb.getBatchPool().replace(mailPackage);
                }
            }
        };

        removalListener = new Function2<Long, MailPackage, Boolean>() {
            @Override public Boolean apply(Long aLong, MailPackage mailPackage) {
                gameDb.getBatchPool().replace(mailPackage);
                log.info("缓存过期移除：{}", mailPackage);
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

    @Override public void put(Long aLong, MailPackage mailPackage) {
        super.put(aLong, mailPackage);
        gameDb.getBatchPool().replace(mailPackage);
    }

    @Override public void putIfAbsent(Long aLong, MailPackage mailPackage) {
        super.putIfAbsent(aLong, mailPackage);
        gameDb.getBatchPool().replace(mailPackage);
    }

}
