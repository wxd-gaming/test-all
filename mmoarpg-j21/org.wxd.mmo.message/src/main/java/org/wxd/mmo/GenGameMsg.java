package org.wxd.mmo;


import org.wxd.boot.core.str.json.FastJsonUtil;
import org.wxd.boot.core.system.JvmUtil;
import org.wxd.boot.net.controller.ProtoBufCreateController;
import org.wxd.boot.net.web.ws.WebSocketServer;

import java.util.TreeMap;

public class GenGameMsg {
    public static void main(String[] args) throws Exception {
        System.out.println(JvmUtil.userHome());
        ProtoBufCreateController protoBufCreateController = new ProtoBufCreateController();

        protoBufCreateController.setProtoSourcePath(JvmUtil.userHome() + "/org.wxd.mmo.message/src/protobuf/game");
        protoBufCreateController.setProtoOutPath(JvmUtil.userHome() + "/org.wxd.mmo.message/src/main/java/");

        protoBufCreateController.buildProtobufToJava("org.wxd.mmo.message/src/protobuf/win64/protoc.exe");

        TreeMap<String, Integer> messageId = protoBufCreateController.createMessageId("Req|Res");
        System.out.println(FastJsonUtil.toJsonFmt(messageId));


        protoBufCreateController.setCodeOutPath("org.wxd.mmo.gamesr-script/src/main/java/");

        protoBufCreateController.createController(
                "proto-controller.ftl",
                "Req",
                ""
        );

    }
}