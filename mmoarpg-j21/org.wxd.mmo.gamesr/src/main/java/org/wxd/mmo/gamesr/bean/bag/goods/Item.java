package org.wxd.mmo.gamesr.bean.bag.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.lang.LNum;
import org.wxd.boot.timer.GameClock;

/**
 * 普通道具
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:31
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Item extends LNum {

    private long uid;
    private int cfgId;
    private boolean bind;
    private long createTime = GameClock.millis();
    /** 未来时间磋 */
    private long expireTime;

    @Override public Item setNum(long num) {
        super.setNum(num);
        return this;
    }
}
