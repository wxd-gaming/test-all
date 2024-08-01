package wxdgaming.mmo.script.loginsr.server;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.lang.RunResult;
import wxdgaming.boot.agent.GlobalUtil;
import wxdgaming.boot.core.threading.ThreadInfo;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.ann.Param;
import wxdgaming.boot.net.controller.ann.TextController;
import wxdgaming.boot.net.controller.ann.TextMapping;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.boot.starter.service.HsService;
import wxdgaming.boot.starter.service.WsService;
import wxdgaming.mmo.core.bean.type.Platforms;
import wxdgaming.mmo.core.bean.type.SdkType;
import wxdgaming.mmo.script.loginsr.event.ScriptEventBus;
import wxdgaming.mmo.script.loginsr.login.ILogin;

/**
 * 和登录服务器rpc通信
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-07-28 19:07
 **/
@Slf4j
@TextController(service = HsService.class)
@TextController(service = WsService.class)
public class RpcController implements IBeanInit {

    @Inject ScriptEventBus eventBus;

    @Override public void beanInit(IocContext iocContext) throws Exception {

    }

    @TextMapping(remarks = "测试心跳")
    public void syncHeart(SocketSession session, ObjMap putData) throws Exception {
        log.debug("心跳");
    }

    @TextMapping
    public String syncGatePort(SocketSession session, ObjMap putData, @Param("sid") Integer sid) throws Exception {
        log.debug("{} sid {}", putData, sid);
        return RunResult.ok().data("回调").toJson();
    }

    @ThreadInfo(vt = true/*虚拟线程执行*/)
    @TextMapping
    public String syncLogin(SocketSession session, ObjMap putData) throws Exception {
        int platform = putData.getIntValue("platform");
        int sdk = putData.getIntValue("sdk");
        final String channel = putData.getString("channel");
        final String account = putData.getString("account");
        String token = putData.getString("token");
        ObjMap ext_param = putData.getObjMap("ext_param");
        Platforms platforms = Platforms.as(platform);
        final SdkType sdkType = SdkType.as(sdk);
        if (!platforms.getSdkTypes().contains(SdkType.All) && !platforms.getSdkTypes().contains(sdkType)) {
            return RunResult.error(99, "不允许使用sdk【" + sdkType + "】登录").toJson();
        }
        try {
            ILogin script = eventBus.script(ILogin.class, sdkType);
            return script.login(platforms, channel, account, token, ext_param).toJson();
        } catch (Exception e) {
            GlobalUtil.exception("platform=【" + platforms + "】, sdk【" + sdkType + "】 登录异常 " + putData.toString(), e);
        }
        return RunResult.error("内部异常").toJson();
    }

}
