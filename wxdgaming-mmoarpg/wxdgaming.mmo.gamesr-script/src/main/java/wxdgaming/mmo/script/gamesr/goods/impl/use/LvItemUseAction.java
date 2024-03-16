package wxdgaming.mmo.script.gamesr.goods.impl.use;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import wxdgaming.mmo.core.bean.bag.ItemGroup;
import wxdgaming.mmo.core.bean.bag.ItemPack;
import wxdgaming.mmo.core.bean.bag.ItemType;
import wxdgaming.mmo.core.bean.bag.OptReason;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.gamesr.data.DataCenter;
import wxdgaming.mmo.script.gamesr.goods.PackModule;
import wxdgaming.mmo.script.gamesr.goods.impl.IAction;

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
