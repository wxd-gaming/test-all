package org.wxd.mmo.core.common.cache.guild;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.Document;
import org.wxd.boot.agent.function.ConsumerE2;
import org.wxd.boot.agent.function.SLFunction1;
import org.wxd.boot.core.cache.CachePack;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.batis.MongoService;
import org.wxd.boot.starter.batis.RedisService;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.login.bean.user.Account;

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
public class AccountCache extends CachePack<Long, Account> implements IBeanInit {

    private static AccountCache instance = null;

    public static AccountCache getInstance() {
        return instance;
    }

    @Inject MongoService mongoService;
    @Inject RedisService redisService;

    public AccountCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        loading = new Function<Long, Account>() {

            @Override public Account apply(Long aLong) {
                Account account = mongoService.queryEntity(Account.class, aLong);
                if (account == null) {
                    log.info("从数据库读取失败：{}", aLong);
                }
                return account;
            }

        };

        unload = new ConsumerE2<Account, String>() {
            @Override public void accept(Account account, String s) throws Exception {

            }
        };

        heart = new Function<Account, Boolean>() {

            @Override public Boolean apply(Account account) {

                return null;
            }

        };

    }

    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
    }

    public long accountDbSize() {
        return mongoService.estimatedDocumentCount(Account.class);
    }

    @Override public void addCache(Long aLong, Account account) {
        super.addCache(aLong, account);
        mongoService.getBatchPool().replace(account);
    }

    /** 这里所有的都会加载 ，包括跨服的数据 */
    public Account load(int sid, String accountName) {
        final Document whereDocument = new Document();
        SLFunction1<Account, Integer> getSId = Account::getSid;
        whereDocument.append(getSId.ofMethodName(), new BsonInt32(sid));

        SLFunction1<Account, String> getAccount = Account::getAccountName;
        whereDocument.append(getAccount.ofMethodName(), new BsonString(accountName));
        Account account = mongoService.queryEntity(Account.class, whereDocument);
        if (account == null) {
            log.info("从数据库读取失败：{}, {}", sid, accountName);
        }
        return account;
    }

}
