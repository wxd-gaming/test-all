package org.wxd.mmo.gamesr.bean.bag;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.wxd.boot.lang.ObjectBase;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:55
 **/
@Getter
@Builder(setterPrefix = "set")
public class ItemCfg extends ObjectBase {

    protected long uid;
    protected int cfgId;
    protected long num;
    protected boolean bind;
    /** 持续时间 毫秒 */
    protected long expire;

}
