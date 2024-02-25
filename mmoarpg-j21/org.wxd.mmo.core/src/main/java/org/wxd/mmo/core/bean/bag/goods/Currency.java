package org.wxd.mmo.core.bean.bag.goods;

/**
 * 货币
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-25 11:41
 **/
public class Currency extends Item {

    @Override public long getUid() {
        return super.getCfgId();
    }

    @Override public Currency setUid(long uid) {
        super.setUid(uid);
        return this;
    }

    @Override public Currency setCfgId(int cfgId) {
        super.setUid(cfgId);
        super.setCfgId(cfgId);
        return this;
    }

    @Override public Currency setBind(boolean bind) {
        super.setBind(bind);
        return this;
    }

    @Override public Currency setCreateTime(long createTime) {
        super.setCreateTime(createTime);
        return this;
    }

    @Override public Currency setExpireTime(long expireTime) {
        super.setExpireTime(expireTime);
        return this;
    }

    @Override public Currency setNum(long num) {
        super.setNum(num);
        return this;
    }
}
