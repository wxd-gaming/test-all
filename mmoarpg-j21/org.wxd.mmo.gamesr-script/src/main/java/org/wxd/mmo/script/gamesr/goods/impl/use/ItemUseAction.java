package org.wxd.mmo.script.gamesr.goods.impl.use;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wxd.mmo.core.bean.bag.ItemGroup;
import org.wxd.mmo.core.bean.bag.ItemPack;
import org.wxd.mmo.core.bean.bag.ItemType;
import org.wxd.mmo.core.bean.bag.OptReason;
import org.wxd.mmo.core.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.gamesr.data.DataCenter;
import org.wxd.mmo.script.gamesr.goods.PackModule;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;

import java.util.Collection;

/**
 * 道具使用
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 11:31
 **/
@Singleton
public class ItemUseAction<T extends Item> implements IAction {

    @Inject DataCenter dataCenter;
    @Inject PackModule packModule;

    @Override public ItemGroup itemGroup() {
        return ItemGroup.None;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    public void use(Player player, ItemPack itemPack, int cfgId, long num, OptReason optReason, String... logs) {
        Collection<Item> items = itemPack.items(cfgId);
        for (Item item : items) {
            if (item.getNum() >= num) {
                packModule.removeItem(player, itemPack, item, num, optReason, logs);
            } else {
                num -= item.getNum();
                packModule.removeItem(player, itemPack, item, item.getNum(), optReason, logs);
            }
        }
    }

    public <T extends Item> void use(Player player, ItemPack itemPack, T item, long num, OptReason optReason, String... logs) {
        packModule.removeItem(player, itemPack, item, num, optReason, logs);
    }

}
