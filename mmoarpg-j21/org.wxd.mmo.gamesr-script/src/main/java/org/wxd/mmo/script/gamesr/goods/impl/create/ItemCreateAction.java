package org.wxd.mmo.script.gamesr.goods.impl.create;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wxd.boot.core.timer.MyClock;
import org.wxd.mmo.core.bean.bag.ItemCfg;
import org.wxd.mmo.core.bean.bag.ItemGroup;
import org.wxd.mmo.core.bean.bag.ItemType;
import org.wxd.mmo.core.bean.bag.goods.Item;
import org.wxd.mmo.core.bean.data.UidSeed;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.gamesr.data.DataCenter;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;

import java.util.List;


/**
 * 道具创建
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class ItemCreateAction<T extends Item> implements IAction {

    @Inject DataCenter dataCenter;

    @Override public ItemGroup itemGroup() {
        return ItemGroup.None;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    public List<T> createItem(Player player, ItemCfg itemCfg) {
        T t = newInstance();
        initItem(t, itemCfg.getCfgId(), itemCfg.getNum(), itemCfg.isBind(), itemCfg.getExpire());
        return List.of(t);
    }

    protected T newInstance() {
        return (T) new Item();
    }

    protected void initItem(T t, int cfgId, long num, boolean bind, long expire) {
        if (num <= 0)
            throw new UnsupportedOperationException(cfgId + " 生成道具数量 " + num + " 异常");
        t
                .setUid(dataCenter.newUid(UidSeed.Type.Goods))
                .setCfgId(cfgId)
                .setNum(num)
                .setBind(bind);
        if (expire > 0) {
            t.setExpireTime(MyClock.millis() + expire);
        }
    }

}
