package wxdgaming.mmo.core.bean.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.core.lang.task.UpdateType;
import wxdgaming.boot.core.lang.task.Condition;
import wxdgaming.boot.core.lang.task.ConditionProgress;

import java.util.HashSet;

/**
 * 成就
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 19:15
 **/
@Getter
@Setter
@Accessors(chain = true)
public class AchieveInfo extends ConditionProgress {

    private AchieveType achieveType;
    private final HashSet<Integer> rewardCfgIds = new HashSet<>();

    /** 实际上是初始化成就类型 */
    @Override public int getCfgId() {
        return super.getCfgId();
    }

    @Override protected Condition condition() {
        return new Condition(1, 0, 0, UpdateType.Add, 100);
    }

}
