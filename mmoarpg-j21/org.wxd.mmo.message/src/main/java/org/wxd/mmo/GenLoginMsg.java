package org.wxd.mmo;


import org.wxd.boot.core.system.JvmUtil;
import org.wxd.boot.net.controller.ProtoBufCreateController;
import org.wxd.boot.net.web.ws.WebSocketServer;

public class GenLoginMsg {
    public static void main(String[] args) throws Exception {
        System.out.println(JvmUtil.userHome());
        ProtoBufCreateController protoBufCreateController = new ProtoBufCreateController();

        protoBufCreateController.setProtoSourcePath(JvmUtil.userHome() + "/org.wxd.mmo.message/src/protobuf/login");
        protoBufCreateController.setProtoOutPath(JvmUtil.userHome() + "/org.wxd.mmo.message/src/main/java/");

        protoBufCreateController.buildProtobufToJava("org.wxd.mmo.message/src/protobuf/win64/protoc.exe");

        protoBufCreateController.createMessageId("Req|Res");
        System.out.println();


        protoBufCreateController.setCodeOutPath("org.wxd.mmo.loginsr-script/src/main/java/");

        protoBufCreateController.createController(
                "Req",
                ""
        );

    }
}