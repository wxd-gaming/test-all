package org.wxd.mmo.web.script.server;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IBeanInit;
import org.wxd.boot.net.web.ws.WebSession;
import org.wxd.boot.net.web.ws.WebSocketServer;


/**
 * 消息处理器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-10-17 14:51
 **/
@Slf4j
@Resource
public class MessageController implements IBeanInit {

    @Resource WebSocketServer<WebSession> webSocketServer;

    @Override public void beanInit(IocInjector iocInjector) throws Exception {
        webSocketServer.setOnStringMessage((webSession, msg) -> {
            log.info(msg);
            webSession.writeAndFlush("收到消息");
        });
    }

}
