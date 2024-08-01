package wxdgaming.mmo.gamesr.bean.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.save.ObjectSave;
import wxdgaming.boot.core.collection.concurrent.ConcurrentSkipTable;
import wxdgaming.mmo.core.bean.task.AchieveInfo;
import wxdgaming.mmo.core.bean.task.AchieveType;
import wxdgaming.mmo.core.bean.task.TaskInfo;
import wxdgaming.mmo.core.bean.task.TaskType;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 任务容器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-07-31 17:24
 **/
@Getter
@Setter
@Accessors(chain = true)
public class TaskPackage extends ObjectSave {

    /** 成就 */
    private final ConcurrentSkipListMap<AchieveType, AchieveInfo> achieves = new ConcurrentSkipListMap<>();
    /** 任务集合 */
    private final ConcurrentSkipTable<TaskType, Integer, TaskInfo> tasks = new ConcurrentSkipTable<>();

}
