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

/**
 * 等级丹的道具使用
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 11:31
 **/
@Singleton
public class LvItemUseAction extends ItemUseAction<Item> implements IAction {

    @Inject DataCenter dataCenter;
    @Inject PackModule packModule;

    @Override public ItemGroup itemGroup() {
        return ItemGroup.ITEM;
    }

    @Override public ItemType itemType() {
        return ItemType.LV;
    }

    @Override public void use(Player player, ItemPack itemPack, int cfgId, long num, OptReason optReason, String... logs) {
        {
            /*todo 增加等级*/
        }
        super.use(player, itemPack, cfgId, num, optReason, logs);
    }

    @Override public void use(Player player, ItemPack itemPack, Item item, long num, OptReason optReason, String... logs) {
        {
            /*todo 增加等级*/
        }
        super.use(player, itemPack, item, num, optReason, logs);
    }

}
