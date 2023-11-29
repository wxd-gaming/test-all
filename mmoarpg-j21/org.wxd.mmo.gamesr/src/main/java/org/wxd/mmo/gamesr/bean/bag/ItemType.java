package org.wxd.mmo.gamesr.bean.bag;


import org.wxd.boot.collection.OfMap;
import org.wxd.boot.lang.IEnum;

import java.util.Map;

/**
 * 道具类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:16
 **/
public enum ItemType implements IEnum {

    None(0, ItemGroup.None, "默认值"),
    Gold(1, ItemGroup.CURRENCY, "钻石"),
    Money(2, ItemGroup.CURRENCY, "游戏金币"),
    Exp(3, ItemGroup.ITEM, "经验值"),
    Equip_Weapon(100, ItemGroup.EQUIP, "武器"),
    Equip_Helmet(101, ItemGroup.EQUIP, "头盔"),
    ;

    private static final Map<Integer, ItemType> static_map = OfMap.asMap(ItemType::getCode, ItemType.values());

    public static ItemType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final ItemGroup itemGroup;
    private final String comment;

    ItemType(int code, ItemGroup itemGroup, String comment) {
        this.code = code;
        this.itemGroup = itemGroup;
        this.comment = comment;
    }

    @Override
    public int getCode() {
        return code;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    @Override
    public String getComment() {
        return comment;
    }
}