package org.wxd.mmo.script.gamesr.goods.impl.create;

import com.google.inject.Singleton;
import org.wxd.boot.core.timer.MyClock;
import org.wxd.mmo.gamesr.bean.bag.ItemCfg;
import org.wxd.mmo.gamesr.bean.bag.ItemGroup;
import org.wxd.mmo.gamesr.bean.bag.ItemType;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;


/**
 * 道具创建
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class ItemCreateAction<T extends Item> implements IAction {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.None;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    public T createItem(Player player, ItemCfg itemCfg) {
        T t = newInstance();
        initItem(player, t, itemCfg);
        return t;
    }

    protected T newInstance() {
        return (T) new Item();
    }

    protected void initItem(Player player, T t, ItemCfg itemCfg) {
        t
                .setCfgId(itemCfg.getCfgId())
                .setNum(itemCfg.getNum())
                .setBind(itemCfg.isBind());
        if (itemCfg.getExpire() > 0) {
            t.setExpireTime(MyClock.millis() + itemCfg.getExpire());
        }
    }

}
