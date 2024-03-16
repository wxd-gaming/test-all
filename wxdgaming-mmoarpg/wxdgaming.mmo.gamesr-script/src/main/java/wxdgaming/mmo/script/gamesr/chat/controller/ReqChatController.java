package wxdgaming.mmo.script.gamesr.chat.controller;

import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.IController;
import wxdgaming.boot.net.controller.ann.ProtoController;
import wxdgaming.boot.net.controller.ann.ProtoMapping;
import wxdgaming.mmo.script.gamesr.chat.message.ReqChat;
import wxdgaming.mmo.script.gamesr.chat.message.ResChat;
import wxdgaming.mmo.gamesr.bean.user.Player;

/**
 * 请求发送聊天 file=Chat.proto
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-14 22:27:10
 */
@Slf4j
@ProtoController()
public final class ReqChatController implements IController {

    /** 请求发送聊天 ReqChat */
    @ProtoMapping(remarks = "请求发送聊天")
    public void exec(SocketSession session, ReqChat req) throws Exception {
        Player player = session.attr("player");
        ResChat.Builder res4Builder = ResChat.newBuilder();
    }
}
