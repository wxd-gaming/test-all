package wxdgaming.mmo.core.common.cache.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.agent.function.ConsumerE2;
import wxdgaming.boot.agent.function.SLFunction1;
import wxdgaming.boot.core.cache.CachePack;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService1;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.core.login.bean.user.Account;

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
public class AccountCache extends CachePack<String, Account> implements IBeanInit {

    @Getter private static AccountCache instance = null;

    @Inject MysqlService1 loginDb;

    public AccountCache() {

        setCacheSurvivalTime(TimeUnit.MINUTES.toMillis(20));
        setCacheHeartTimer(TimeUnit.MINUTES.toMillis(1));
        setCacheIntervalTime(TimeUnit.MINUTES.toMillis(1));

        loading = this::load;

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
        loginDb.createTable(Account.class);
    }

    public long accountDbSize() {
        return loginDb.rowCount(Account.class);
    }

    @Override public void addCache(String accountName, Account account) {
        super.addCache(accountName, account);
        loginDb.getBatchPool().replace(account);
    }

    /** 这里所有的都会加载 ，包括跨服的数据 */
    public Account load(String accountName) {
        SLFunction1<Account, String> getAccount = Account::getAccountName;
        Account account = loginDb.queryEntityByWhere(Account.class, getAccount.ofMethodName() + "=?", accountName);
        if (account == null) {
            log.info("从数据库读取失败：{}", accountName);
        }
        return account;
    }

}
