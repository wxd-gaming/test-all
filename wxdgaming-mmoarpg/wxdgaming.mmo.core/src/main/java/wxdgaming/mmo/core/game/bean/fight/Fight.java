package wxdgaming.mmo.core.game.bean.fight;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.core.lang.LNum;
import wxdgaming.mmo.core.GameBase;

/**
 * 战斗对象
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-07 15:05
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Fight extends GameBase {

    protected String name;
    protected int model;
    /** 当前等级 */
    protected int level;
    /** 战力 */
    protected long fightPower;
    /** 当前经验 */
    protected LNum exp = new LNum();

    public Fight() {
    }

    public Fight(long uid) {
        super(uid);
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("{");
        sb.append("uid=").append(uid);
        sb.append(", name=").append(name);
        sb.append(", lv=").append(level);
        sb.append('}');
        return sb.toString();
    }
}
