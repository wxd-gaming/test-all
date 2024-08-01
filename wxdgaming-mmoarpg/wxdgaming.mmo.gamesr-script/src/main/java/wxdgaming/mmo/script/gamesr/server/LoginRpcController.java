package wxdgaming.mmo.script.gamesr.server;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.timer.ann.Scheduled;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.ann.TextController;
import wxdgaming.boot.net.controller.ann.TextMapping;
import wxdgaming.boot.net.web.ws.WebSession;
import wxdgaming.boot.net.web.ws.WebSocketClient;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.gamesr.data.DataCenter;
import wxdgaming.mmo.script.gamesr.login.message.ReqLogin;


/**
 * 和登录服务器rpc通信
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-07-28 19:07
 **/
@Slf4j
@TextController()
public class LoginRpcController implements IBeanInit {

    @Inject DataCenter dataCenter;

    @Override public void beanInit(IocContext iocContext) throws Exception {
        if (dataCenter.getLoginSocket() == null) {
            dataCenter.setLoginSocket(new WebSocketClient<>().setName("login-client").setHost("127.0.0.1").setPort(18100));
        }
    }

    @Scheduled("*/5")
    public void s() {
        WebSocketClient<WebSession> loginSocket = dataCenter.getLoginSocket();
        if (loginSocket == null) return;
        loginSocket.checked();

        WebSession loginSession = loginSocket.idleSession();
        if (loginSession == null) return;

        /* rpc调用示例，和 登录服务器同步心跳 */
        loginSession.rpc(
                "/Rpc/syncHeart",
                "1", 1
        ).send();

        /*rpc调用示例，向登录服务器获取网关监听结果 */
        loginSession.rpc(
                        "/Rpc/syncGatePort",
                        "sid", 1
                )
                .async(
                        rpcEvent -> {
                            log.info(rpcEvent.getResJson());
                        }
                );

        ReqLogin.Builder builder = ReqLogin.newBuilder();
        loginSession.write(builder);

    }

    @TextMapping
    public void syncGatePort(SocketSession session, ObjMap putData) {

    }

}
