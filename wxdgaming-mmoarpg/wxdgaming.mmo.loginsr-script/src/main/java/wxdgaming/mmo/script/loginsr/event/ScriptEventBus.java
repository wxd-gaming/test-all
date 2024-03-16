package wxdgaming.mmo.script.loginsr.event;


import com.google.inject.Singleton;
import wxdgaming.boot.starter.EventBusBase;

/**
 * 时间触发器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:26
 **/
@Singleton
public class ScriptEventBus extends EventBusBase {

    public interface RegisterScript {}

    public interface LoginBefore {
        void onLoginBefore();
    }

    public interface LoginAfter {
        void onLoginAfter();
    }

}
