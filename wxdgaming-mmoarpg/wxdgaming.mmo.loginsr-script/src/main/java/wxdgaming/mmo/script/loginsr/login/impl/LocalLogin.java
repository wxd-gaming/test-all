package wxdgaming.mmo.script.loginsr.login.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.lang.RunResult;
import wxdgaming.boot.core.str.Md5Util;
import wxdgaming.mmo.core.bean.type.Platforms;
import wxdgaming.mmo.core.bean.type.SdkType;
import wxdgaming.mmo.script.loginsr.login.ILogin;
import wxdgaming.mmo.script.loginsr.login.LoginModule;

/**
 * 本地登录，调试系统登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 15:29
 **/
@Singleton
public class LocalLogin implements ILogin {

    @Inject LoginModule loginModule;

    @Override public SdkType scriptKey() {
        return SdkType.Local;
    }

    @Override public RunResult login(Platforms platforms, String channel, String channelAccount, String token, ObjMap extMap) {
        /*本地登录token就是默认token无需验证*/
        token = Md5Util.md5DigestEncode(token, TOKEN_MD5_SIGN);
        return loginModule.login(platforms, scriptKey(), channel, channelAccount, channelAccount, token, extMap);
    }

}
