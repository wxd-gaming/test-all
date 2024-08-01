package wxdgaming.mmo.script.loginsr.login;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.lang.RunResult;
import wxdgaming.boot.core.str.Md5Util;
import wxdgaming.boot.core.system.MarkTimer;
import wxdgaming.boot.core.timer.MyClock;
import wxdgaming.boot.core.timer.ann.Scheduled;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService1;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.core.bean.config.ServerConfig;
import wxdgaming.mmo.core.bean.type.Platforms;
import wxdgaming.mmo.core.bean.type.SdkType;
import wxdgaming.mmo.core.common.cache.user.AccountCache;
import wxdgaming.mmo.core.login.bean.user.Account;
import wxdgaming.mmo.loginsr.data.DataCenter;
import wxdgaming.mmo.script.loginsr.event.ScriptEventBus;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 登录模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-04 19:53
 **/
@Slf4j
@Singleton
public class LoginModule implements IBeanInit {

    @Inject private MysqlService1 loginDb;
    @Inject DataCenter dataCenter;
    @Inject ServerConfig serverConfig;
    @Inject AccountCache accountCache;
    @Inject ScriptEventBus scriptEventBus;

    @Override public void beanInit(IocContext iocContext) throws Exception {
        HashSet<SdkType> sdkTypes = new HashSet<>();
        for (SdkType sdkType : SdkType.values()) {
            if (sdkType.getCode() <= SdkType.All.getCode()) continue;
            List<ILogin> collect = scriptEventBus.scripts(ILogin.class, sdkType).toList();
            if (collect.isEmpty()) {
                throw new RuntimeException("存在两个 sdk=【" + sdkType + "】 没有实现登录接口 ");
            }
            if (collect.size() > 1) {
                throw new RuntimeException("存在两个 sdk=【" + sdkType + "】 登录接口 " + collect.stream().map(v -> v.getClass().getSimpleName()).collect(Collectors.joining()));
            }
        }

        scriptEventBus.beanStream(ILogin.class).forEach(iLogin -> {
            if (!sdkTypes.add(iLogin.scriptKey())) {
                throw new RuntimeException("存在两个 sdk=【" + iLogin.scriptKey() + "】 登录接口 " + iLogin.getClass());
            }
        });

    }

    Account userFirst = null;

    @Scheduled("*/5")
    public void test() {
        {
            MarkTimer markTimer = MarkTimer.build();
            log.info("当前账号总数：{}, 缓存数：{} {}", accountCache.accountDbSize(), accountCache.cacheSize(), markTimer.execTime2String());
        }
    }

    /**
     * 登录
     *
     * @param channel        渠道
     * @param channelAccount 渠道账号
     * @param accountName    自己的账号体系对于
     * @param token          秘钥
     * @param extMap         扩展参数
     * @return
     * @author: wxd-gaming(無心道, 15388152619)
     * @version: 2024-02-27 10:00
     */
    public RunResult login(Platforms platforms, SdkType sdkType, String channel, String channelAccount, String accountName, String token, ObjMap extMap) {
        // synchronized (accountName.intern()) {/*虚拟线程会死锁，后续考虑怎么处理*/
        Account account = accountCache.get(accountName);
        if (account == null) {
            /*不存在就直接创建*/
            account = new Account();
            account.setUid(dataCenter.getServerData().newAccountUid(serverConfig.getSid()));
            account.setAccountName(accountName);
            account.setToken(token);
            account.setChannel(channel);
            account.setChannelAccountName(channelAccount);
            account.setCreateTime(MyClock.millis());
            /*存储*/
            accountCache.put(account.getAccountName(), account);
        }

        if (!Objects.equals(account.getToken(), token)) {
            return RunResult.error("密码错误");
        }

        account.setLastLoginTime(MyClock.millis());
        account.setLoginChannel(channel);

        return RunResult
                .ok()
                .append("uid", account.getUid())
                .append("accountName", account.getAccountName())
                .append("channelAccountName", account.getChannelAccountName())
                .append("token", Md5Util.md5DigestEncode(platforms.name(), accountName, channelAccount, ILogin.TOKEN_MD5_SIGN))/*内部分布式token*/
                ;
        //}
    }

}
