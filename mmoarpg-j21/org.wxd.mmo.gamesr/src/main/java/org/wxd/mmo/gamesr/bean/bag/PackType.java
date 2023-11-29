package org.wxd.mmo.gamesr.bean.bag;

import org.wxd.boot.collection.OfMap;
import org.wxd.boot.lang.IEnum;

import java.util.Map;

/**
 * 背包类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 15:15
 **/
public enum PackType implements IEnum {
    None(0, "默认值"),
    BAG(1, "随身背包"),
    STORE(2, "仓库"),
    ;

    private static final Map<Integer, PackType> static_map = OfMap.asMap(PackType::getCode, PackType.values());

    public static PackType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    PackType(int code, String comment) {
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