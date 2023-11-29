package org.wxd.mmo.gamesr.bean.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.collection.Table;
import org.wxd.boot.lang.save.ObjectSave;
import org.wxd.boot.lang.task.Condition;
import org.wxd.mmo.gamesr.bean.task.impl.TaskInfo;
import org.wxd.mmo.gamesr.bean.task.impl.TaskType;

import java.util.ArrayList;

/**
 * 任务容器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 17:24
 **/
@Getter
@Setter
@Accessors(chain = true)
public class TaskPackage extends ObjectSave {

    /** 成就 */
    private ArrayList<Condition> achieves = new ArrayList<>();
    /** 任务集合 */
    private final Table<TaskType, Integer, TaskInfo> tasks = new Table<>();

}
