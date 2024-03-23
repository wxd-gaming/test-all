package code.impl;

import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.boot.net.controller.TextMappingProxy;
import wxdgaming.mmo.script.loginsr.server.RpcController;

import java.util.concurrent.atomic.AtomicReference;

public class TProxy extends TextMappingProxy {

    public void proxy(Object out, Object instance, Object[] params) throws Throwable {
        AtomicReference atomicReference = (AtomicReference) out;

        String ret = null;
        ret = ((RpcController) instance).syncGatePort(
                (SocketSession) params[0],
                (ObjMap) params[1],
                (Integer) params[2]
        );
        atomicReference.set(ret);

    }

}
