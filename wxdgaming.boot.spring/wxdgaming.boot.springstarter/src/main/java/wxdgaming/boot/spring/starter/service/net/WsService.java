package wxdgaming.boot.spring.starter.service.net;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wxdgaming.boot.net.web.ws.WebSession;
import wxdgaming.boot.net.web.ws.WebSocketServer;
import wxdgaming.boot.spring.starter.config.NetConfig;
import wxdgaming.boot.spring.starter.config.bean.TcpConfig;
import wxdgaming.boot.spring.starter.i.OnClose;
import wxdgaming.boot.spring.starter.i.OnStart;

/**
 * web socket service
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-12-11 17:49
 **/
@Order(1)
@Component
@ConditionalOnProperty(value = "net-config.webSocket.port")
public class WsService extends WebSocketServer<WebSession> {

    public WsService(NetConfig netConfig) throws Exception {
        TcpConfig config = netConfig.getWebSocket();
        setName(config.getName())
                .setHost(config.getHost())
                .setWanIp(config.getWanIp())
                .setPort(config.getPort())
                .setSslType(config.sslProtocolType())
                .setSslContext(config.sslContext())
                .initBootstrap();
    }

    @OnClose
    @Override public void close() {
        super.close();
    }

    @Order(3)
    @OnStart
    @Override public void open() {
        super.open();
    }
}
