package org.wxd.mmo.core.bean.task;


import org.wxd.boot.core.lang.task.Condition;
import org.wxd.boot.core.lang.task.TaskBase;

/**
 * 任务
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 17:13
 **/
public class TaskInfo extends TaskBase {

    @Override protected Condition condition() {
        /*自己考虑通过 cfgId 获取*/
        return super.condition();
    }

}
