package wxdgaming.mmo.script.gamesr.task.impl;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.mmo.core.bean.task.TaskInfo;
import wxdgaming.mmo.core.bean.task.TaskType;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.event.ScriptEventBus;
import wxdgaming.mmo.script.gamesr.task.ITaskImpl;

import java.util.Map;

/**
 * 主线任务的实现
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-23 19:38
 **/
@Slf4j
@Singleton
public class GuildTaskImpl implements ITaskImpl, ScriptEventBus.PlayerLoginBefore {

    @Override public TaskType scriptKey() {
        return TaskType.Main;
    }

    /**
     * 登录完成之前
     *
     * @param player
     */
    @Override public void onLoginBefore(Player player) {
        Map<Integer, TaskInfo> integerTaskInfoMap = player.getTaskPackage().getTasks().get(scriptKey());
        if (integerTaskInfoMap.isEmpty()) {
            /*todo 初始化公会任务*/
        }
    }

    @Override public boolean acceptTask(Player player, int cfgId) {
        return false;
    }

    @Override public boolean finishTask(Player player, int cfgId) {
        return false;
    }

}
