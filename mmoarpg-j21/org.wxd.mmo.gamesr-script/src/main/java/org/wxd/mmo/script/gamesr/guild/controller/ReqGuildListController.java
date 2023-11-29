package org.wxd.mmo.script.gamesr.guild.controller;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.IController;
import org.wxd.boot.net.controller.ann.ProtoController;
import org.wxd.boot.net.controller.ann.ProtoMapping;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.guild.message.ReqGuildList;
import org.wxd.mmo.script.gamesr.guild.message.ResGuildList;

/**
 * 公会列表 file=Guild.proto
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 21:32:03
 */
@Slf4j
@ProtoController(service = "org.wxd.boot.net.web.ws.WebSocketServer")
public final class ReqGuildListController implements IController {

    /** 公会列表 ReqGuildList */
    @ProtoMapping(remarks = "公会列表")
    public void exec(SocketSession session, ReqGuildList reqMessage) throws Exception {
        Player player = (Player) session.attr("player");
        ResGuildList.Builder res4Builder = ResGuildList.newBuilder();
    }

}
