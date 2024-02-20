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
public class ExpChangeAction extends ItemChangeAction<Item> {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.ITEM;
    }

    @Override public ItemType itemType() {
        return ItemType.Exp;
    }

    @Override protected void add0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        player.getExp().add(item.getNum());
    }

    @Override public void add0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        player.getExp().add(itemCfg.getNum());
    }

    @Override protected void remove0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        player.getExp().sub(item.getNum());
    }

    @Override public void remove0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        player.getExp().sub(itemCfg.getNum());
    }

    public long itemNum(Player player, PackType packType, int cfg) {
        return player.getExp().getNum();
    }

    public long itemNum(Player player, ItemPack itemPack, int cfg) {
        return player.getExp().getNum();
    }

}
