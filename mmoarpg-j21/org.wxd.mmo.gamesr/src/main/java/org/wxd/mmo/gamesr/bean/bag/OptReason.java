package org.wxd.mmo.gamesr.bean.bag;


import org.wxd.boot.core.collection.OfMap;
import org.wxd.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 操作原因
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 15:14
 **/
public enum OptReason implements IEnum {
    None(0, "默认值"),
    ;

    private static final Map<Integer, OptReason> static_map = OfMap.asMap(OptReason::getCode, OptReason.values());

    public static OptReason as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    OptReason(int code, String comment) {
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