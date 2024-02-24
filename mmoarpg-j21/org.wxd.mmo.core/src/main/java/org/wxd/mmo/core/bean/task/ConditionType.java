package org.wxd.mmo.core.bean.task;

import org.wxd.boot.core.collection.MapOf;
import org.wxd.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 完成条件
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-24 18:00
 **/
public enum ConditionType implements IEnum {

    None(0, "默认值"),
    LoginCount(1, "累计登录"),
    LoginDayCount(1, "累计登录"),
    ;

    private static final Map<Integer, ConditionType> static_map = MapOf.asMap(ConditionType::getCode, ConditionType.values());

    public static ConditionType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    ConditionType(int code, String comment) {
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