package org.wxd.mmo.core.bean.type;


import lombok.Getter;
import org.wxd.boot.core.collection.MapOf;
import org.wxd.boot.core.lang.IEnum;

import java.util.Map;
import java.util.Set;

/**
 * 渠道枚举
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 15:59
 **/
@Getter
public enum Platforms implements IEnum {
    None(0, "默认值"),
    All(1, "通用", SdkType.All),
    Local(2, "本地渠道", SdkType.Local),
    ;

    private static final Map<Integer, Platforms> static_map = MapOf.asMap(Platforms::getCode, Platforms.values());

    public static Platforms as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;
    private final Set<SdkType> sdkTypes;

    Platforms(int code, String comment, SdkType... sdkTypes) {
        this.code = code;
        this.comment = comment;
        this.sdkTypes = Set.of(sdkTypes);
    }

}