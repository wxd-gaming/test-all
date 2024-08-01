package wxdgaming.mmo.core.bean.task;


import wxdgaming.boot.core.lang.task.UpdateType;
import wxdgaming.boot.core.lang.task.Condition;
import wxdgaming.boot.core.lang.task.TaskBase;

/**
 * 任务
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-07-31 17:13
 **/
public class TaskInfo extends TaskBase {

    @Override protected Condition condition() {
        /*自己考虑通过 cfgId 获取*/
        return new Condition(1, 0, 0, UpdateType.Add, 100);
    }

}
