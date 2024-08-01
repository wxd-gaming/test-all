package wxdgaming.mmo.core.common.cache.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.agent.function.Consumer2;
import wxdgaming.boot.agent.function.Function2;
import wxdgaming.boot.agent.function.SLFunction1;
import wxdgaming.boot.core.lang.Cache;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService1;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.core.login.bean.user.Account;

import java.util.concurrent.TimeUnit;

/**
 * 账号缓存容器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-03 14:38
 **/
@Slf4j
@Singleton
public class AccountCache extends Cache<String, Account> implements IBeanInit {

    @Getter private static AccountCache instance = null;

    @Inject MysqlService1 loginDb;

    public AccountCache() {
        super(TimeUnit.MINUTES.toMillis(1));
        expireAfterAccess = (TimeUnit.MINUTES.toMillis(20));

        loader = this::load;

        removalListener = new Function2<String, Account, Boolean>() {
            @Override public Boolean apply(String string, Account account) {

                return null;
            }
        };

        heartTime = (TimeUnit.MINUTES.toMillis(1));
        heartListener = new Consumer2<String, Account>() {
            @Override public void accept(String string, Account account) {

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

    @Override public void put(String string, Account account) {
        super.put(string, account);
        loginDb.getBatchPool().replace(account);
    }

    @Override public void putIfAbsent(String string, Account account) {
        super.putIfAbsent(string, account);
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
