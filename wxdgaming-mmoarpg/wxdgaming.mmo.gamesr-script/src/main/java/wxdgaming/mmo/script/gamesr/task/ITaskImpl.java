package wxdgaming.mmo.script.gamesr.task;

import wxdgaming.boot.starter.EventBusBase;
import wxdgaming.mmo.core.bean.task.TaskType;
import wxdgaming.mmo.gamesr.bean.user.Player;

/**
 * 任务类型实现
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 19:40
 **/
public interface ITaskImpl extends EventBusBase.IScript<TaskType> {

    default boolean acceptTask(Player player, int cfgId) {return false;}

    default boolean finishTask(Player player, int cfgId) {return false;}

}
