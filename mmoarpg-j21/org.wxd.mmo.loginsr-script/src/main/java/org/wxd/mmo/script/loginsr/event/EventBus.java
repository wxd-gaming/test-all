package org.wxd.mmo.script.loginsr.event;


import org.wxd.boot.ioc.EventBusBase;
import org.wxd.boot.ioc.ann.Resource;


/**
 * 时间触发器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:26
 **/
@Resource
public class EventBus extends EventBusBase {


    public interface RegisterScript {}

    public interface LoginBefore {
        void onLoginBefore();
    }

    public interface LoginAfter {
        void onLoginAfter();
    }

}