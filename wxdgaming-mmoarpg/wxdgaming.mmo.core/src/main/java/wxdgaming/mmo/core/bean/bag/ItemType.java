package wxdgaming.mmo.core.bean.bag;


import wxdgaming.boot.core.collection.MapOf;
import wxdgaming.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 道具类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-07 20:16
 **/
public enum ItemType implements IEnum {

    None(0, ItemGroup.None, "默认值"),
    Gold(1, ItemGroup.CURRENCY, "钻石"),
    Money(2, ItemGroup.CURRENCY, "游戏金币"),
    Exp(3, ItemGroup.CURRENCY, "经验值"),
    LV(4, ItemGroup.CURRENCY, "等级丹"),
    Equip_Weapon(100, ItemGroup.EQUIP, "武器"),
    Equip_Helmet(101, ItemGroup.EQUIP, "头盔"),
    ;

    private static final Map<Integer, ItemType> static_map = MapOf.asMap(ItemType::getCode, ItemType.values());

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