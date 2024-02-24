package org.wxd.mmo.gamesr.bean.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.save.ObjectSave;
import org.wxd.boot.core.collection.concurrent.ConcurrentListTable;
import org.wxd.mmo.core.bean.task.AchieveInfo;
import org.wxd.mmo.core.bean.task.AchieveType;
import org.wxd.mmo.core.bean.task.TaskInfo;
import org.wxd.mmo.core.bean.task.TaskType;

import java.util.concurrent.ConcurrentSkipListMap;

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
    private final ConcurrentSkipListMap<AchieveType, AchieveInfo> achieves = new ConcurrentSkipListMap<>();
    /** 任务集合 */
    private final ConcurrentListTable<TaskType, Integer, TaskInfo> tasks = new ConcurrentListTable<>();

}
