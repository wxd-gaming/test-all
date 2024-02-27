package org.wxd.mmo.script.loginsr.login;


import com.alibaba.fastjson.JSONObject;
import org.wxd.boot.core.collection.ObjMap;
import org.wxd.boot.core.lang.RunResult;
import org.wxd.boot.starter.EventBusBase;
import org.wxd.mmo.core.bean.type.Platforms;
import org.wxd.mmo.core.bean.type.SdkType;

/**
 * 登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 19:59
 **/
public interface ILogin extends EventBusBase.IScript<SdkType> {

    static final String TOKEN_MD5_SIGN = "ddddd";

    RunResult login(Platforms platforms, String channel, String channelAccount, String token, ObjMap extMap);

}
