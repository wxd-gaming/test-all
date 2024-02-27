package org.wxd.mmo.script.loginsr.login.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wxd.boot.core.collection.ObjMap;
import org.wxd.boot.core.lang.RunResult;
import org.wxd.boot.core.str.Md5Util;
import org.wxd.boot.net.http.client.url.HttpBuilder;
import org.wxd.mmo.core.bean.type.Platforms;
import org.wxd.mmo.core.bean.type.SdkType;
import org.wxd.mmo.script.loginsr.login.ILogin;
import org.wxd.mmo.script.loginsr.login.LoginModule;

/**
 * 本地登录，调试系统登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 15:29
 **/
@Singleton
public class XiaoqiLogin implements ILogin {


    @Inject LoginModule loginModule;

    @Override public SdkType scriptKey() {
        return SdkType.Xiaoqi;
    }

    @Override public RunResult login(Platforms platforms, String channel, String channelAccount, String token, ObjMap extMap) {
        /* todo sdk 相关的验证 */
        ObjMap sdkCheckParams = new ObjMap()
                .append("uid", channelAccount)
                .append("token", token)
                .append("sign", "md5");

        /*调用方实现异步即可，这里用同步调用sdk服务器*/
        String response = HttpBuilder.postMulti("").putParams(sdkCheckParams).setUrlEncoder(true).request().bodyUnicodeDecodeString();

        /*小七聚合sdk，传递的channelaccount是平台uid，并且小米的uid和华为的uid会重复 所以我们自己的账户需要增加渠道标记，得到唯一值*/
        String accountName = channel + "_" + channelAccount;
        /*固定的秘钥串，符合我们自己的逻辑 兼容本地账号*/
        token = Md5Util.md5DigestEncode(accountName, TOKEN_MD5_SIGN);

        return loginModule.login(platforms, scriptKey(), channel, channelAccount, accountName, token, extMap);
    }

}
