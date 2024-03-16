package wxdgaming.mmo.web.script.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.boot.starter.service.WsService;


/**
 * 消息处理器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-10-17 14:51
 **/
@Slf4j
@Singleton
public class MessageController implements IBeanInit {

    @Inject WsService wsService;

    @Override public void beanInit(IocContext iocContext) throws Exception {
        wsService.setOnStringMessage((webSession, msg) -> {
            log.info(msg);
            webSession.writeAndFlush("收到消息");
        });
    }

}
