package org.wxd.mmo.core.bean.type;

import org.wxd.boot.core.collection.MapOf;
import org.wxd.boot.core.lang.IEnum;

import java.util.Map;

/**
 * sdk 类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-25 12:24
 **/
public enum SdkType implements IEnum {
    None(0, "默认值"),
    All(1, "默认值"),
    Local(2, "本地渠道"),
    Xiaoqi(3, "小七"),
    ;

    private static final Map<Integer, SdkType> static_map = MapOf.asMap(SdkType::getCode, SdkType.values());

    public static SdkType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    SdkType(int code, String comment) {
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