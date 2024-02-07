package org.wxd.mmo.script.gamesr.goods.impl.change;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.gamesr.bean.bag.*;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;


/**
 * 添加道具进道具容器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 15:06
 **/
@Slf4j
@Singleton
public class MoneyChangeAction<T extends Item> extends ItemChangeAction<T> {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.CURRENCY;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    @Override protected void add0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        itemPack.getMoneys().merge(itemCfg.getCfgId(), itemCfg.getNum(), Math::addExact);
    }

    @Override protected void add0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        itemPack.getMoneys().merge(item.getCfgId(), item.getNum(), Math::addExact);
    }

    @Override protected void remove0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        itemPack.getMoneys().merge(item.getCfgId(), -item.getNum(), (n1, n2) -> Math.max(0, Math.addExact(n1, n2)));
    }

    @Override protected void remove0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        itemPack.getMoneys().merge(itemCfg.getCfgId(), -itemCfg.getNum(), (n1, n2) -> Math.max(0, Math.addExact(n1, n2)));
    }

    protected long itemNum(Player player, PackType packType, int cfg) {
        return itemNum(player, player.getItemPackMap().get(packType), cfg);
    }

    protected long itemNum(Player player, ItemPack itemPack, int cfg) {
        return itemPack.getMoneys().getOrDefault(cfg, 0L);
    }

}
