package org.wxd.mmo.script.loginsr.login;


import org.wxd.boot.core.lang.RunResult;
import org.wxd.boot.starter.EventBusBase;
import org.wxd.mmo.core.bean.type.SdkType;

/**
 * 登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 19:59
 **/
public interface ILogin extends EventBusBase.IScript<SdkType> {

    RunResult login(String channel, String account, String token, Object... objects);

}
