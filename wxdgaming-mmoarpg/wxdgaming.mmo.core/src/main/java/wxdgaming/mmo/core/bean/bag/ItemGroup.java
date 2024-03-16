package wxdgaming.mmo.core.bean.bag;


import wxdgaming.boot.core.collection.MapOf;
import wxdgaming.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 道具类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:16
 **/
public enum ItemGroup implements IEnum {
    None(0, "默认值"),
    ITEM(1, "普通道具"),
    CURRENCY(2, "货币"),
    EQUIP(3, "装备");

    private static final Map<Integer, ItemGroup> static_map = MapOf.asMap(ItemGroup::getCode, ItemGroup.values());

    public static ItemGroup as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    ItemGroup(int code, String comment) {
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