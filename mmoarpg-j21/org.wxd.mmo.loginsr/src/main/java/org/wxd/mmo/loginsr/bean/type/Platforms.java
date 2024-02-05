package org.wxd.mmo.loginsr.bean.type;


import org.wxd.boot.core.collection.MapOf;
import org.wxd.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 渠道枚举
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 15:59
 **/
public enum Platforms implements IEnum {
    None(0, "默认值"),
    Local(1, "本地渠道"),
    ;

    private static final Map<Integer, Platforms> static_map = MapOf.asMap(Platforms::getCode, Platforms.values());

    public static Platforms as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    Platforms(int code, String comment) {
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