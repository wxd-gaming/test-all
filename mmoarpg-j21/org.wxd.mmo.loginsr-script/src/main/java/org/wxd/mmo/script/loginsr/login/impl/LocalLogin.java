package org.wxd.mmo.script.loginsr.login.impl;

import com.google.inject.Singleton;
import org.wxd.boot.net.web.hs.HttpSession;
import org.wxd.mmo.script.loginsr.login.ILogin;
import org.wxd.mmo.loginsr.bean.type.Platforms;

import java.io.Serializable;

/**
 * 本地登录，调试系统登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 15:29
 **/
@Singleton
public class LocalLogin implements ILogin<HttpSession> {


    @Override public Serializable scriptKey() {
        return Platforms.Local;
    }

    @Override public void login(HttpSession session) {

    }

}
