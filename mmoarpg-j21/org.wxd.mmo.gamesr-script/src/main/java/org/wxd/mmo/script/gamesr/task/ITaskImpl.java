package org.wxd.mmo.script.gamesr.task;

import org.wxd.boot.starter.EventBusBase;
import org.wxd.mmo.core.bean.task.TaskType;
import org.wxd.mmo.gamesr.bean.user.Player;

import java.io.Serializable;

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
