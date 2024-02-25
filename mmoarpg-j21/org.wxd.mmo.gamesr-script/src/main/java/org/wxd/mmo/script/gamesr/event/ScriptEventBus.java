package org.wxd.mmo.script.gamesr.event;

import com.google.inject.Singleton;
import org.wxd.boot.starter.EventBusBase;
import org.wxd.mmo.gamesr.bean.user.Player;

/**
 * 时间触发器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:26
 **/
@Singleton
public class ScriptEventBus extends EventBusBase {


    public interface RegisterScript {}

    /** 登录验证成功，进入游戏之前 */
    public interface PlayerLoginBefore {
        /** 登录验证成功，进入游戏之前 */
        void onLoginBefore(Player player);
    }

    /** 登录完成之后。 */
    public interface PlayerLoginAfter {
        /** 登录完成之后 */
        void onLoginAfter(Player player);
    }

    /** 跨天执行。 */
    public interface PlayerDayEnd {
        /** 跨天执行 */
        void onPlayerDayEnd(Player player);
    }

}
