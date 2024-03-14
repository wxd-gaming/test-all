package org.wxd.mmo.script.gamesr.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.collection.ObjMap;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.IController;
import org.wxd.boot.net.controller.MessageController;
import org.wxd.boot.net.controller.ann.ProtoController;
import org.wxd.boot.net.controller.ann.ProtoMapping;
import org.wxd.mmo.script.gamesr.chat.message.ReqChat;
import org.wxd.mmo.script.gamesr.chat.message.ResChat;
import org.wxd.mmo.gamesr.bean.user.Player;

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
