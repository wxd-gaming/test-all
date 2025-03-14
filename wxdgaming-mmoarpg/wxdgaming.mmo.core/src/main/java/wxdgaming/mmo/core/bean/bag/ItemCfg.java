package wxdgaming.mmo.core.bean.bag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import wxdgaming.boot.core.lang.ObjectBase;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-07 20:55
 **/
@Getter
@AllArgsConstructor
@Builder(setterPrefix = "set", toBuilder = true)
public class ItemCfg extends ObjectBase {

    protected long uid;
    protected int cfgId;
    protected long num;
    protected boolean bind;
    /** 持续时间 毫秒 */
    protected long expire;

    public ItemType itemType() {
        return ItemType.as(cfgId);
    }

}
