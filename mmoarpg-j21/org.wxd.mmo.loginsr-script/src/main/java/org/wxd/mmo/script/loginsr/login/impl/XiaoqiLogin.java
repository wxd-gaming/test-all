package org.wxd.mmo.script.loginsr.login.impl;

import com.google.inject.Singleton;
import org.wxd.boot.core.lang.RunResult;
import org.wxd.mmo.core.bean.type.SdkType;
import org.wxd.mmo.script.loginsr.login.ILogin;

/**
 * 本地登录，调试系统登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 15:29
 **/
@Singleton
public class XiaoqiLogin implements ILogin {

    @Override public SdkType scriptKey() {
        return SdkType.Xiaoqi;
    }

    @Override public RunResult login(String channel, String account, String token, Object... objects) {
        RunResult ok = RunResult.ok();
        return ok;
    }

}
