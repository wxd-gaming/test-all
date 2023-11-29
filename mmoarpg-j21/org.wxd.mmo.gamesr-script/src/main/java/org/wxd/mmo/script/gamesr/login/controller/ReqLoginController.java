package org.wxd.mmo.script.gamesr.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.IController;
import org.wxd.boot.net.controller.ann.ProtoController;
import org.wxd.boot.net.controller.ann.ProtoMapping;
import org.wxd.mmo.script.gamesr.login.message.ReqLogin;
import org.wxd.mmo.script.gamesr.login.message.ResLogin;

/**
 * 登录消息 file=Login.proto
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 21:32:03
 */
@Slf4j
@ProtoController(service = "org.wxd.boot.net.web.ws.WebSocketServer")
public final class ReqLoginController implements IController {

    /** 登录消息 ReqLogin */
    @ProtoMapping(remarks = "登录消息")
    public void exec(SocketSession session, ReqLogin reqMessage) throws Exception {

        ResLogin.Builder res4Builder = ResLogin.newBuilder();

    }

}
