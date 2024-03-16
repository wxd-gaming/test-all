package wxdgaming.mmo.script.loginsr.login;


import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.lang.RunResult;
import wxdgaming.boot.starter.EventBusBase;
import wxdgaming.mmo.core.bean.type.Platforms;
import wxdgaming.mmo.core.bean.type.SdkType;

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
