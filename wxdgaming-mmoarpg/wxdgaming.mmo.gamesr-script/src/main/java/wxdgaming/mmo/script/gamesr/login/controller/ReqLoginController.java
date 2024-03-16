package wxdgaming.mmo.script.gamesr.login.controller;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.IController;
import wxdgaming.boot.net.controller.ann.ProtoController;
import wxdgaming.boot.net.controller.ann.ProtoMapping;
import wxdgaming.boot.starter.service.WsService;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.event.ScriptEventBus;
import wxdgaming.mmo.script.gamesr.login.message.ReqLogin;
import wxdgaming.mmo.script.gamesr.login.message.ResLogin;

/**
 * 登录消息 file=Login.proto
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 21:32:03
 */
@Slf4j
@ProtoController(service = WsService.class)
public final class ReqLoginController implements IController {

    @Inject ScriptEventBus eventBus;

    /** 登录消息 ReqLogin */
    @ProtoMapping(remarks = "登录消息")
    public void exec(SocketSession session, ReqLogin reqMessage) throws Exception {

        ResLogin.Builder res4Builder = ResLogin.newBuilder();

        Player player = null;

        eventBus.forEachBean(ScriptEventBus.PlayerLoginBefore.class, eventBean -> {
            /*todo 登录之前事件抛出，比如这里可以做家长数据，上线前的检测，跨天等操作*/
            eventBean.onLoginBefore(player);
        });

        /*todo 做其他事情*/

        eventBus.forEachBean(ScriptEventBus.PlayerLoginAfter.class, eventBean -> {
            /*todo 抛出登录完成事件*/
            eventBean.onLoginAfter(player);
        });

    }

}
