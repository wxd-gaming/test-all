package org.wxd.mmo.script.loginsr.login;


import org.wxd.boot.ioc.EventBusBase;
import org.wxd.boot.net.Session;

/**
 * 登录
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 19:59
 **/
public interface ILogin<S extends Session> extends EventBusBase.IScript {

    void login(S session);

}
