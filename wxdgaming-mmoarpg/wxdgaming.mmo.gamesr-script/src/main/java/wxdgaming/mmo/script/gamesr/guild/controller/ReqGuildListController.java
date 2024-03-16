package wxdgaming.mmo.script.gamesr.guild.controller;

import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.IController;
import wxdgaming.boot.net.controller.ann.ProtoController;
import wxdgaming.boot.net.controller.ann.ProtoMapping;
import wxdgaming.mmo.script.gamesr.guild.message.ReqGuildList;
import wxdgaming.mmo.script.gamesr.guild.message.ResGuildList;
import wxdgaming.mmo.gamesr.bean.user.Player;

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
