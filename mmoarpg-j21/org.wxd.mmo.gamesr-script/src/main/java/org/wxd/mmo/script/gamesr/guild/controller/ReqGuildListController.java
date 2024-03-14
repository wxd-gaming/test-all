package org.wxd.mmo.script.gamesr.guild.controller;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.collection.ObjMap;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.IController;
import org.wxd.boot.net.controller.MessageController;
import org.wxd.boot.net.controller.ann.ProtoController;
import org.wxd.boot.net.controller.ann.ProtoMapping;
import org.wxd.mmo.script.gamesr.guild.message.ReqGuildList;
import org.wxd.mmo.script.gamesr.guild.message.ResGuildList;
import org.wxd.mmo.gamesr.bean.user.Player;

/**
 * 公会列表 file=Guild.proto
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-14 22:27:10
 */
@Slf4j
@ProtoController()
public final class ReqGuildListController implements IController {

    /** 公会列表 ReqGuildList */
    @ProtoMapping(remarks = "公会列表")
    public void exec(SocketSession session, ReqGuildList req) throws Exception {
        Player player = session.attr("player");
        ResGuildList.Builder res4Builder = ResGuildList.newBuilder();
    }
}
