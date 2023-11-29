package org.wxd.mmo.gamesr.bean.task.impl;


import org.wxd.boot.collection.OfMap;
import org.wxd.boot.lang.IEnum;

import java.util.Map;

/**
 * 任务类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 17:25
 **/
public enum TaskType implements IEnum {
    None(0, "默认值"),
    Main(1, "主线"),
    Branch(2, "支线"),
    ;

    private static final Map<Integer, TaskType> static_map = OfMap.asMap(TaskType::getCode, TaskType.values());

    public static TaskType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    TaskType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getComment() {
        return comment;
    }
}