package org.wxd.mmo.core.bean.task;

import org.wxd.boot.core.collection.MapOf;
import org.wxd.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 成就类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 19:24
 **/
public enum AchieveType implements IEnum {
    None(0, "默认值"),
    ;

    private static final Map<Integer, AchieveType> static_map = MapOf.asMap(AchieveType::getCode, AchieveType.values());

    public static AchieveType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    AchieveType(int code, String comment) {
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