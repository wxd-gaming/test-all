package wxdgaming.mmo.core.bean.bag;


import wxdgaming.boot.core.collection.MapOf;
import wxdgaming.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 操作原因
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-08 15:14
 **/
public enum OptReason implements IEnum {
    None(0, "默认值"),
    INIT_PLAYER(1, "角色初始化"),
    USE(2, "使用"),
    TASK_ACCEPT(3, "接取任务"),
    TASK_FINISH(4, "完成任务"),
    ;

    private static final Map<Integer, OptReason> static_map = MapOf.asMap(OptReason::getCode, OptReason.values());

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