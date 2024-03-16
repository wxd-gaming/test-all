package wxdgaming.mmo;


import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.net.controller.ProtoBufCreateController;
import wxdgaming.boot.net.web.ws.WebSocketServer;

public class GenLoginMsg {
    public static void main(String[] args) throws Exception {
        System.out.println(JvmUtil.userHome());
        ProtoBufCreateController protoBufCreateController = new ProtoBufCreateController();

        protoBufCreateController.setProtoSourcePath(JvmUtil.userHome() + "/wxdgaming.mmo.message/src/protobuf/login");
        protoBufCreateController.setProtoOutPath(JvmUtil.userHome() + "/wxdgaming.mmo.message/src/main/java/");

        protoBufCreateController.buildProtobufToJava("wxdgaming.mmo.message/src/protobuf/win64/protoc.exe");

        protoBufCreateController.createMessageId("Req|Res");
        System.out.println();


        protoBufCreateController.setCodeOutPath("wxdgaming.mmo.loginsr-script/src/main/java/");

        protoBufCreateController.createController(
                "Req",
                ""
        );

    }
}