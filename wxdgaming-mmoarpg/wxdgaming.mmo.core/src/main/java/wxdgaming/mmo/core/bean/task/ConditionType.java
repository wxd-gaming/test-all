package wxdgaming.mmo.core.bean.task;

import wxdgaming.boot.core.lang.task.UpdateKey;

/**
 * 完成条件
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-24 18:00
 **/
public interface ConditionType {

    /** 登录次数 */
    UpdateKey LoginCount = new UpdateKey("loginDays");
    /** 当前等级 */
    UpdateKey Level = new UpdateKey("Level");
    /** 等级提升 */
    UpdateKey LevelUp = new UpdateKey("LevelUp");
    /** 杀怪 */
    UpdateKey KillMonster = new UpdateKey("KillMonster");

}