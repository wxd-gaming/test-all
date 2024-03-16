package wxdgaming.mmo.core.bean.task;

import wxdgaming.boot.core.collection.MapOf;
import wxdgaming.boot.core.lang.IEnum;

import java.util.Map;

/**
 * 任务类型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 17:25
 **/
public enum TaskType implements IEnum {

    None(0, "默认值"),
    Main(1, "主线"),
    Branch1(2, "支线"),
    Branch2(3, "支线"),
    Branch3(4, "支线"),
    Branch4(5, "支线"),
    Guild(6, "公会"),
    ;

    private static final Map<Integer, TaskType> static_map = MapOf.asMap(TaskType::getCode, TaskType.values());

    public static TaskType as(int value) {
        return static_map.get(value);
    }

    private final int code;
    private final String comment;

    TaskType(int code, String comment) {
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