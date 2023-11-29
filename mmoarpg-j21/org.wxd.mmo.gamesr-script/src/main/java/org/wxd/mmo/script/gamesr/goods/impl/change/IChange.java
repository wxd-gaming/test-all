package org.wxd.mmo.script.gamesr.goods.impl.change;

import org.wxd.mmo.gamesr.bean.bag.ItemCfg;
import org.wxd.mmo.gamesr.bean.bag.ItemPack;
import org.wxd.mmo.gamesr.bean.bag.OptReason;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;

/**
 * 变更
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 16:11
 **/
public interface IChange {

    void add(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs);

    void remove(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs);

    void add(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs);

    void remove(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs);


}
