package org.wxd.mmo.script.gamesr.gm.controller;

import com.alibaba.fastjson2.JSONArray;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.collection.ListOf;
import org.wxd.boot.net.SocketSession;
import org.wxd.boot.net.controller.IController;
import org.wxd.boot.net.controller.ann.ProtoController;
import org.wxd.boot.net.controller.ann.ProtoMapping;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.gm.GmMappingInfo;
import org.wxd.mmo.script.gamesr.gm.GmModule;
import org.wxd.mmo.script.gamesr.gm.message.ReqGm;
import org.wxd.mmo.script.gamesr.tips.TipsModule;


/**
 * 请求执行gm命令--没有回复 file=Gm.proto
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023/02/10 15:03:58
 */
@Slf4j
@ProtoController(service = "org.wxd.boot.net.web.ws.WebSocketServer")
public final class ReqGmController implements IController {

    @Inject GmModule gmModule;
    @Inject TipsModule tipsModule;

    /** 请求执行gm命令--没有回复 ReqGm */
    @ProtoMapping(remarks = "请求执行gm命令--没有回复")
    public void exec(SocketSession session, ReqGm reqMessage) throws Exception {
        Player player = (Player) session.attr("player");

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
            ListOf.asList(jsonArray, reqMessage.getParams().split(",|，"));
            gmMappingInfo.getMethod().invoke(gmMappingInfo.getInstance(), player, jsonArray);
        } catch (Throwable throwable) {
            log.error("执行gm命令：" + player.playerSnap().logName() + " - " + reqMessage.getName() + " - " + reqMessage.getParams(), throwable);
        }

    }

}
