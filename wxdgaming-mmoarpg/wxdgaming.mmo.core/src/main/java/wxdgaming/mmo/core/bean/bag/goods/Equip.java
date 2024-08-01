package wxdgaming.mmo.core.bean.bag.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 装备
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-01 16:48
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Equip extends Item {

    /** 战斗力 */
    private long fightPower = 0;

    @Override public Equip setUid(long uid) {
        super.setUid(uid);
        return this;
    }

    @Override public Equip setNum(long num) {
        super.setNum(1);
        return this;
    }

    @Override public long getNum() {
        return 1;
    }

    @Override public Equip setBind(boolean bind) {
        super.setBind(bind);
        return this;
    }

    @Override public Equip setCfgId(int cfgId) {
        super.setCfgId(cfgId);
        return this;
    }

    @Override public Equip setCreateTime(long createTime) {
        super.setCreateTime(createTime);
        return this;
    }

    @Override public Equip setExpireTime(long expireTime) {
        super.setExpireTime(expireTime);
        return this;
    }

}
