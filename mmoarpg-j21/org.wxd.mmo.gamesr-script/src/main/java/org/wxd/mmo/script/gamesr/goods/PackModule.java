package org.wxd.mmo.script.gamesr.goods;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.gamesr.bean.bag.ItemCfg;
import org.wxd.mmo.gamesr.bean.bag.ItemPack;
import org.wxd.mmo.gamesr.bean.bag.OptReason;
import org.wxd.mmo.gamesr.bean.bag.PackType;
import org.wxd.mmo.gamesr.bean.user.Player;

import java.util.Collection;

/**
 * 背包模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:29
 **/
@Slf4j
@Singleton
public class PackModule {

    public void add(Player player, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        add(player, PackType.BAG, items, optReason, logs);
    }

    public void add(Player player, PackType packType, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        add(player, player.getItemPackMap().get(packType), items, optReason, logs);
    }

    public void add(Player player, ItemPack itemPack, Collection<ItemCfg> items, OptReason optReason, String... logs) {

        for (ItemCfg itemCfg : items) {
            add(player, itemPack, itemCfg, optReason, logs);
        }

    }

    public void add(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {


    }
}
