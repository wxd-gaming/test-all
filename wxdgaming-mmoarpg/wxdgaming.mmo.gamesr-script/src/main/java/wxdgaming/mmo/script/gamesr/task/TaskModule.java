package wxdgaming.mmo.script.gamesr.task;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.lang.task.UpdateKey;
import wxdgaming.mmo.core.bean.task.ConditionType;
import wxdgaming.mmo.core.bean.task.TaskType;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.event.ScriptEventBus;

/**
 * 任务模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 19:37
 **/
@Slf4j
@Singleton
public class TaskModule implements ScriptEventBus.PlayerLoginAfter, ScriptEventBus.PlayerDayEnd {

    @Inject ScriptEventBus eventBus;

    /**
     * 登录完成之后
     *
     * @param player
     */
    @Override public void onLoginAfter(Player player) {
    }

    /**
     * 跨天执行
     *
     * @param player
     */
    @Override public void onPlayerDayEnd(Player player) {
        /*累计登录事件计数*/
        change(player, ConditionType.LoginCount, 1);
    }

    public void change(Player player, UpdateKey k1, long progress) {
        change(player, k1, UpdateKey.NONE, progress);
    }

    public void change(Player player, UpdateKey k1, UpdateKey k2, long progress) {
        change(player, k1, k2, UpdateKey.NONE, progress);
    }

    public void change(Player player, UpdateKey conditionType, UpdateKey k2, UpdateKey k3, long progress) {

        player.getTaskPackage().getAchieves().values().forEach(condition -> {
            boolean change = condition.change(conditionType, k2, k3, progress);
            if (change) {
                /*通知客户端变更*/
                log.info("{} 成就变更 {}", player, condition);
            }
        });

        player.getTaskPackage().getTasks().forEach(taskInfo -> {
            boolean change = taskInfo.change(conditionType, k2, k3, progress);
            if (change) {
                /*通知客户端变更*/
                log.info("{} 任务变更 {}", player, taskInfo);
            }
        });

    }

    /** 接取任务 */
    public void acceptTask(Player player, TaskType taskType, int cfgId) {

        eventBus.scripts(ITaskImpl.class, taskType, script -> {
            script.acceptTask(player, cfgId);
        });

    }

    /** 完成任务 */
    public void finishTask(Player player, TaskType taskType, int cfgId) {

        eventBus.scripts(ITaskImpl.class, taskType, script -> {
            script.finishTask(player, cfgId);
        });

    }

}
