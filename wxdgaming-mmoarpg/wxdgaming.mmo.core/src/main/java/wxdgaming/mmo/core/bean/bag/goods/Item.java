package wxdgaming.mmo.core.bean.bag.goods;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.core.lang.LNum;
import wxdgaming.boot.core.timer.MyClock;
import wxdgaming.mmo.core.bean.bag.ItemType;

/**
 * 普通道具
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 20:31
 **/
@Getter
@Setter
@Accessors(chain = true)
public class Item extends LNum {

    private long uid;
    private int cfgId;
    private boolean bind;
    private long createTime = MyClock.millis();
    /** 未来时间磋 */
    private long expireTime;

    public ItemType itemType() {
        return ItemType.as(cfgId);
    }

    @Override public Item setNum(long num) {
        super.setNum(num);
        return this;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("{");
        sb.append("uid=").append(uid);
        sb.append(", cfgId=").append(cfgId);
        sb.append(", bind=").append(bind);
        sb.append(", num=").append(getNum());
        sb.append('}');
        return sb.toString();
    }
}
