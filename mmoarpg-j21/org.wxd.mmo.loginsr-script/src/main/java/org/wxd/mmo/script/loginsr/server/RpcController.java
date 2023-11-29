package org.wxd.mmo.script.loginsr.server;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.collection.ObjMap;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.i.IBeanInit;
import org.wxd.boot.lang.RunResult;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.ann.TextController;
import org.wxd.boot.net.controller.ann.TextMapping;

/**
 * 和登录服务器rpc通信
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-28 19:07
 **/
@Slf4j
@TextController(service = "org.wxd.boot.net.web.ws.WebSocketServer")
public class RpcController implements IBeanInit {

    @Override public void beanInit(IocInjector iocInjector) throws Exception {

    }

    @TextMapping(remarks = "测试心跳")
    public void syncHeart(SocketSession session, ObjMap putData) {
        log.debug("心跳");
    }

    @TextMapping
    public String syncGatePort(SocketSession session, ObjMap putData) {
        log.debug("{}", putData);
        return RunResult.ok1().setData("回调").toJson();
    }


}
