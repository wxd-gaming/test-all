package org.wxd.mmo.script.gamesr.server;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.collection.ObjMap;
import org.wxd.boot.ioc.ActionTextController;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IBeanInit;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.ann.TextController;
import org.wxd.boot.net.controller.ann.TextMapping;
import org.wxd.boot.net.web.ws.WebSession;
import org.wxd.boot.net.web.ws.WebSocketClient;
import org.wxd.boot.timer.ann.Scheduled;
import org.wxd.mmo.gamesr.data.DataCenter;


/**
 * 和登录服务器rpc通信
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-28 19:07
 **/
@Slf4j
@Resource
@TextController(alligatorAutoRegister = true)
public class LoginRpcController implements IBeanInit {

    @Resource DataCenter dataCenter;

    @Override public void beanInit(IocInjector iocInjector) throws Exception {
        if (dataCenter.getLoginSocket() == null) {
            dataCenter.setLoginSocket(new WebSocketClient<>().setName("login-client").setHost("127.0.0.1").setPort(17900));
        }
        /*把自己和链接控制器绑定*/
        ActionTextController.bindCmd(dataCenter.getLoginSocket(), this);
    }

    @Scheduled("*/5")
    public void s() {
        WebSocketClient<WebSession> loginSocket = dataCenter.getLoginSocket();
        if (loginSocket == null) return;
        loginSocket.checked();

        WebSession loginSession = loginSocket.idleSession();
        if (loginSession == null) return;

        loginSession.rpc(
                "Rpc/syncHeart",
                "1", 1
        ).send();

        loginSession.rpc(
                "Rpc/syncGatePort",
                "1",
                1
        ).async(rpcEvent -> {log.info(rpcEvent.getResJson());}, null, throwable -> log.info("", throwable));

    }

    @TextMapping
    public void syncGatePort(SocketSession session, ObjMap putData) {

    }

}
