package wxdgaming.mmo.core.bean.task;

import wxdgaming.boot.core.lang.task.UpdateKey;

/**
 * 完成条件
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-24 18:00
 **/
public interface ConditionType {

    /** 登录次数 */
    UpdateKey LoginCount = new UpdateKey(1);
    /** 当前等级 */
    UpdateKey Lv = new UpdateKey(3);
    /** 等级提升 */
    UpdateKey LvUp = new UpdateKey(4);
    /** 杀怪 */
    UpdateKey KillMonster = new UpdateKey(5);

}