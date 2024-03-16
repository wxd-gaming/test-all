package wxdgaming.mmo;


import wxdgaming.boot.core.str.json.FastJsonUtil;
import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.net.controller.ProtoBufCreateController;
import wxdgaming.boot.net.web.ws.WebSocketServer;

import java.util.TreeMap;

public class GenGameMsg {
    public static void main(String[] args) throws Exception {
        System.out.println(JvmUtil.userHome());
        ProtoBufCreateController protoBufCreateController = new ProtoBufCreateController();

        protoBufCreateController.setProtoSourcePath(JvmUtil.userHome() + "/wxdgaming.mmo.message/src/protobuf/game");
        protoBufCreateController.setProtoOutPath(JvmUtil.userHome() + "/wxdgaming.mmo.message/src/main/java/");

        protoBufCreateController.buildProtobufToJava("wxdgaming.mmo.message/src/protobuf/win64/protoc.exe");

        TreeMap<String, Integer> messageId = protoBufCreateController.createMessageId("Req|Res");
        System.out.println("所有的消息ID");
        System.out.println(FastJsonUtil.toJsonFmt(messageId));
        System.out.println("");

        protoBufCreateController.setCodeOutPath("wxdgaming.mmo.gamesr-script/src/main/java/");

        protoBufCreateController.createController(
                "proto-controller.ftl",
                "Req",
                ""
        );

    }
}