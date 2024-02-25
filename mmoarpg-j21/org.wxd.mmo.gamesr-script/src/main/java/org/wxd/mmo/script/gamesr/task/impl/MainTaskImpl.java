package org.wxd.mmo.script.gamesr.task.impl;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.core.bean.task.TaskInfo;
import org.wxd.mmo.core.bean.task.TaskType;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.event.ScriptEventBus;
import org.wxd.mmo.script.gamesr.task.ITaskImpl;

import java.util.Map;

/**
 * 主线任务的实现
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 19:38
 **/
@Slf4j
@Singleton
public class MainTaskImpl implements ITaskImpl, ScriptEventBus.PlayerLoginBefore {

    @Override public TaskType taskType() {
        return TaskType.Main;
    }

    /**
     * 登录完成之前
     *
     * @param player
     */
    @Override public void onLoginBefore(Player player) {
        Map<Integer, TaskInfo> integerTaskInfoMap = player.getTaskPackage().getTasks().get(taskType());
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
