package wxdgaming.mmo.script.gamesr.goods.impl.use;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.mmo.core.bean.bag.ItemGroup;
import wxdgaming.mmo.core.bean.bag.ItemPack;
import wxdgaming.mmo.core.bean.bag.ItemType;
import wxdgaming.mmo.core.bean.bag.OptReason;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.gamesr.data.DataCenter;
import wxdgaming.mmo.script.gamesr.goods.PackModule;
import wxdgaming.mmo.script.gamesr.goods.impl.IAction;

import java.util.Collection;

/**
 * 道具使用
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-23 11:31
 **/
@Slf4j
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
                num = 0;
            } else {
                num -= item.getNum();
                packModule.removeItem(player, itemPack, item, item.getNum(), optReason, logs);
            }
            if (num <= 0)
                break;
        }
    }

    public <T extends Item> void use(Player player, ItemPack itemPack, T item, long num, OptReason optReason, String... logs) {
        packModule.removeItem(player, itemPack, item, num, optReason, logs);
    }

}
