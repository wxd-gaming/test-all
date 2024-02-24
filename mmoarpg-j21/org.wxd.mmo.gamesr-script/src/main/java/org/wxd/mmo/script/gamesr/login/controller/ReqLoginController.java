package org.wxd.mmo.script.gamesr.login.controller;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.IController;
import org.wxd.boot.net.controller.ann.ProtoController;
import org.wxd.boot.net.controller.ann.ProtoMapping;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.event.EventBus;
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

    @Inject EventBus eventBus;

    /** 登录消息 ReqLogin */
    @ProtoMapping(remarks = "登录消息")
    public void exec(SocketSession session, ReqLogin reqMessage) throws Exception {

        ResLogin.Builder res4Builder = ResLogin.newBuilder();

        Player player = null;

        eventBus.forEachBean(EventBus.PlayerLoginBefore.class, eventBean -> {
            /*todo 登录之前事件抛出，比如这里可以做家长数据，上线前的检测，跨天等操作*/
            eventBean.onLoginBefore(player);
        });

        /*todo 做其他事情*/

        eventBus.forEachBean(EventBus.PlayerLoginAfter.class, eventBean -> {
            /*todo 抛出登录完成事件*/
            eventBean.onLoginAfter(player);
        });

    }

}
