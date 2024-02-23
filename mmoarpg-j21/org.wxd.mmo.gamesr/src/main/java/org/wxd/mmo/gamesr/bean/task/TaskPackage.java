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

    public void change(int k1, int k2, int k3, long progress) {

        achieves.values().forEach(condition -> {
            boolean change = condition.change(k1, k2, k3, progress);
            if (change) {
                /*通知客户端变更*/
            }
        });

        tasks.forEach(taskInfo -> {
            boolean change = taskInfo.change(k1, k2, k3, progress);
            if (change) {
                /*通知客户端变更*/
            }
        });

    }

}
