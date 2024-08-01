package wxdgaming.mmo.core.bean.rank;

import wxdgaming.boot.core.collection.MapOf;
import wxdgaming.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 排行榜类型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-07 20:30
 **/
public enum RankType implements IEnum {
    None(0, "默认值"),
    Power(1, "战力"),
    Level(2, "等级"),
    ;

    private static final Map<Integer, RankType> static_map = MapOf.asMap(RankType::getCode, RankType.values());

    public static RankType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    RankType(int code, String comment) {
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