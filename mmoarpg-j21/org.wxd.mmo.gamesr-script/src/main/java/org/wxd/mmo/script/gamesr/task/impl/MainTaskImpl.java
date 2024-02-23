package org.wxd.mmo.script.gamesr.task.impl;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.core.bean.task.TaskType;
import org.wxd.mmo.script.gamesr.task.ITaskImpl;

/**
 * 主线任务的实现
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 19:38
 **/
@Slf4j
@Singleton
public class MainTaskImpl implements ITaskImpl {

    @Override public TaskType taskType() {
        return TaskType.Main;
    }

}
