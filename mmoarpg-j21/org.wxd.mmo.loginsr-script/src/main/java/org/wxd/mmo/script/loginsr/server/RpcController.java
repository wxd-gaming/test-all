package org.wxd.mmo.script.loginsr.server;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.collection.ObjMap;
import org.wxd.boot.core.lang.RunResult;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.ann.TextController;
import org.wxd.boot.net.controller.ann.TextMapping;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.bean.type.Platforms;
import org.wxd.mmo.core.bean.type.SdkType;
import org.wxd.mmo.script.loginsr.event.ScriptEventBus;
import org.wxd.mmo.script.loginsr.login.ILogin;

/**
 * 和登录服务器rpc通信
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-28 19:07
 **/
@Slf4j
@TextController
public class RpcController implements IBeanInit {

    @Inject ScriptEventBus eventBus;

    @Override public void beanInit(IocContext iocContext) throws Exception {

    }

    @TextMapping(remarks = "测试心跳")
    public void syncHeart(SocketSession session, ObjMap putData) {
        log.debug("心跳");
    }

    @TextMapping
    public String syncGatePort(SocketSession session, ObjMap putData) {
        log.debug("{}", putData);
        return RunResult.ok().data("回调").toJson();
    }

    @TextMapping
    public String syncLogin(SocketSession session, ObjMap putData) {

        int platform = putData.getIntValue("platform");
        int sdk = putData.getIntValue("sdk");
        final String channel = putData.getString("channel");
        final String account = putData.getString("account");
        String token = putData.getString("token");
        Platforms platforms = Platforms.as(platform);
        final SdkType sdkType = SdkType.as(sdk);
        ILogin script = eventBus.script(ILogin.class, sdkType);
        script.login(channel, account, token);

        return RunResult.ok().data("回调").toJson();
    }

}
