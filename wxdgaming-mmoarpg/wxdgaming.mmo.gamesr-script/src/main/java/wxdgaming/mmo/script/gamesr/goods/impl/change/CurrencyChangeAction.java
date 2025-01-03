package wxdgaming.mmo.script.gamesr.goods.impl.change;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.lang.LNum;
import wxdgaming.mmo.core.bean.bag.*;
import wxdgaming.mmo.core.bean.bag.goods.Currency;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.gamesr.bean.user.Player;


/**
 * 货币变更处理
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-08 15:06
 **/
@Slf4j
@Singleton
public class CurrencyChangeAction<T extends Currency> extends ItemChangeAction<T> {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.CURRENCY;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    @Override protected void add0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        itemPack.getMoneys().merge(item.getCfgId(), item.getNum(), Math::addExact);
    }

    @Override protected void remove0(Player player, ItemPack itemPack, Item item, LNum itemCfgNum, OptReason optReason, String... logs) {
        Long aLong = itemPack.getMoneys().get(item.getCfgId());
        if (aLong != null) {
            if (aLong >= itemCfgNum.getNum()) {
                itemPack.getMoneys().put(item.getCfgId(), aLong - itemCfgNum.getNum());
                itemCfgNum.setNum(0);
            } else {
                itemCfgNum.sub(aLong);
                itemPack.getMoneys().put(item.getCfgId(), 0L);
            }
        }
    }

    public long itemNum(Player player, PackType packType, int cfg) {
        return itemNum(player, player.getItemPackMap().get(packType), cfg);
    }

    public long itemNum(Player player, ItemPack itemPack, int cfg) {
        return itemPack.getMoneys().getOrDefault(cfg, 0L);
    }

}
