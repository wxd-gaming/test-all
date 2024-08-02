package wxdgaming.boot.spring.starter.service.net;

import io.netty.buffer.ByteBuf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import wxdgaming.boot.net.ts.TcpServer;
import wxdgaming.boot.net.ts.TcpSession;
import wxdgaming.boot.spring.starter.config.NetConfig;
import wxdgaming.boot.spring.starter.config.bean.TcpConfig;
import wxdgaming.boot.spring.starter.i.OnClose;
import wxdgaming.boot.spring.starter.i.OnStart;

/**
 * tcp server
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-12-11 18:20
 **/
@Component
@ConditionalOnProperty(value = "net-config.tcpSocket1.port")
public class TsService1 extends TcpServer<TcpSession> {

    @Autowired
    public TsService1(NetConfig netConfig) throws Exception {
        TcpConfig config = netConfig.getTcpSocket1();
        setName(config.getName())
                .setHost(config.getHost())
                .setWanIp(config.getWanIp())
                .setPort(config.getPort())
                .setSslType(config.sslProtocolType())
                .setSslContext(config.sslContext())
                .initBootstrap();
    }

    @Override public void read(TcpSession session, ByteBuf byteBuf) {
        super.read(session, byteBuf);
    }

    @OnClose
    @Override public void close() {
        super.close();
    }

    @Order(1)
    @OnStart
    @Override public void open() {
        super.open();
    }
}
