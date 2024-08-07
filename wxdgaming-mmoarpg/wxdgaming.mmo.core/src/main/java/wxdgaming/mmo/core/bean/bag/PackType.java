package wxdgaming.mmo.core.bean.bag;

import wxdgaming.boot.core.collection.MapOf;
import wxdgaming.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 背包类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-08 15:15
 **/
public enum PackType implements IEnum {
    None(0, "默认值"),
    BAG(1, "随身背包"),
    STORE(2, "随身仓库"),
    NPC_STORE(3, "NPC仓库"),
    GUILD(10, "公会"),
    ;

    private static final Map<Integer, PackType> static_map = MapOf.asMap(PackType::getCode, PackType.values());

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