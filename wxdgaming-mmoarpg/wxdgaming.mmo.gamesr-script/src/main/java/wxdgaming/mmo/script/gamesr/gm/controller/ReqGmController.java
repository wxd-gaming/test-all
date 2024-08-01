package wxdgaming.mmo.script.gamesr.gm.controller;

import com.alibaba.fastjson2.JSONArray;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.collection.ListOf;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.IController;
import wxdgaming.boot.net.controller.ann.ProtoController;
import wxdgaming.boot.net.controller.ann.ProtoMapping;
import wxdgaming.boot.starter.service.WsService;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.gm.GmMappingInfo;
import wxdgaming.mmo.script.gamesr.gm.GmModule;
import wxdgaming.mmo.script.gamesr.gm.message.ReqGm;
import wxdgaming.mmo.script.gamesr.tips.TipsModule;


/**
 * 请求执行gm命令--没有回复 file=Gm.proto
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023/02/10 15:03:58
 */
@Slf4j
@ProtoController(service = WsService.class)
public final class ReqGmController implements IController {

    @Inject GmModule gmModule;
    @Inject TipsModule tipsModule;

    /** 请求执行gm命令--没有回复 ReqGm */
    @ProtoMapping(remarks = "请求执行gm命令--没有回复")
    public void exec(SocketSession session, ReqGm reqMessage) throws Exception {
        Player player = session.attr("player");

        GmMappingInfo gmMappingInfo = gmModule.getGmMap().get(reqMessage.getName());
        if (gmMappingInfo == null) {
            tipsModule.sendTips(player, "gm 命令异常");
            return;
        }
        if (player.playerSnap().getGmLv() < gmMappingInfo.getGm().gmLv()) {
            tipsModule.sendTips(player, "gm 命令 权限不足");
            return;
        }

        try {
            JSONArray jsonArray = new JSONArray();
            ListOf.asList(jsonArray, reqMessage.getParams().split("[,，]"));
            gmMappingInfo.getMethod().invoke(gmMappingInfo.getInstance(), player, jsonArray);
        } catch (Throwable throwable) {
            log.error("执行gm命令：" + player.playerSnap().logName() + " - " + reqMessage.getName() + " - " + reqMessage.getParams(), throwable);
        }

    }

}
