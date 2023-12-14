package org.wxd.mmo.script.gamesr.event;

import com.google.inject.Singleton;
import org.wxd.boot.starter.EventBusBase;

/**
 * 时间触发器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:26
 **/
@Singleton
public class EventBus extends EventBusBase {


    public interface RegisterScript {}

    public interface LoginBefore {
        void onLoginBefore();
    }

    public interface LoginAfter {
        void onLoginAfter();
    }

}
