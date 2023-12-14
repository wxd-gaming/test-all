package org.wxd.mmo.script.loginsr.login;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.boot.system.MarkTimer;
import org.wxd.boot.timer.ann.Scheduled;
import org.wxd.mmo.bean.config.ServerConfig;
import org.wxd.mmo.common.cache.user.AccountCache;
import org.wxd.mmo.login.bean.user.Account;
import org.wxd.mmo.loginsr.data.DataCenter;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 19:53
 **/
@Slf4j
@Singleton
public class LoginModule implements IBeanInit {

    @Inject ServerConfig serverConfig;
    @Inject AccountCache accountCache;
    @Inject DataCenter dataCenter;
    private ConcurrentHashMap<Serializable, ILogin> loginScriptMap = new ConcurrentHashMap<>();

    @Override public void beanInit(IocContext iocContext) throws Exception {
        iocContext.forEachBean(ILogin.class, iLogin -> {
            if (loginScriptMap.containsKey(iLogin.scriptKey())) {
                throw new RuntimeException("登录sdk重复：" + iLogin.scriptKey());
            }
            loginScriptMap.put(iLogin.scriptKey(), iLogin);
        });
    }

    Account userFirst = null;

    @Scheduled("*/5")
    public void test() {
        {
            MarkTimer markTimer = MarkTimer.build();
            log.info("当前账号总数：{}, 缓存数：{} {}", accountCache.accountDbSize(), accountCache.cacheSize(), markTimer.execTime2String());
        }
        {
            MarkTimer markTimer = MarkTimer.build();
            int f = 10;
            for (int i = 0; i < f; i++) {
                Account account = new Account();
                account.setUid(dataCenter.getServerData().newAccountUid(serverConfig.getSid()));
                account.setAccountName("test1");
                account.setChannel("local");
                account.setLoginChannel("local");
                accountCache.addCache(account.getUid(), account);

                if (userFirst == null) {
                    userFirst = account;
                }
            }
            log.info("创建 {} 条数据{}", f, markTimer.execTime2String());
        }
    }

}
