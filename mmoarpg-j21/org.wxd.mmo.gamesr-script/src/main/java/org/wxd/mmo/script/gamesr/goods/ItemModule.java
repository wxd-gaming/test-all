package org.wxd.mmo.script.gamesr.goods;


import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.bean.bag.ItemCfg;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * 道具模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:31
 **/
@Slf4j
@Singleton
public class ItemModule implements IBeanInit {

    @Override public void beanInit(IocContext iocContext) throws Exception {

    }

    /** 做合并操作 */
    public Collection<ItemCfg> marge(Collection<ItemCfg> cfgs) {
        HashMap<Integer, List<ItemCfg>> cfgMap = new HashMap<>();
        for (ItemCfg cfg : cfgs) {
            cfgMap.computeIfAbsent(cfg.getCfgId(), l -> new ArrayList<>()).add(cfg);
        }
        return cfgs;
    }

}
