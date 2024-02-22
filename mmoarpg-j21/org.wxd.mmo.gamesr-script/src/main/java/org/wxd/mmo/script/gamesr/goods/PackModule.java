package org.wxd.mmo.script.gamesr.goods;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.core.bean.bag.*;
import org.wxd.mmo.gamesr.bean.bag.*;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.impl.ItemAction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 背包模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:29
 **/
@Slf4j
@Singleton
public class PackModule {

    @Inject ItemAction itemAction;

    /** 扣除道具前判定背包是否足够 */
    public boolean isEnough(Player player, ItemPack itemPack, Collection<ItemCfg> cfgs) {
        HashMap<Integer, Long> idNums = new HashMap<>();
        for (ItemCfg cfg : cfgs) {
            idNums.merge(cfg.getCfgId(), cfg.getNum(), Math::addExact);
        }
        for (Map.Entry<Integer, Long> entry : idNums.entrySet()) {
            if (!isEnough(player, itemPack, entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    /** 扣除道具前判定背包是否足够 */
    public boolean isEnough(Player player, ItemPack itemPack, ItemCfg itemCfg) {
        return isEnough(player, itemPack, itemCfg.getCfgId(), itemCfg.getNum());
    }

    /** 扣除道具前判定背包是否足够 */
    public boolean isEnough(Player player, ItemPack itemPack, int cfgId, long needNum) {
        ItemType itemType = ItemType.as(cfgId);
        long num = itemAction.changeAction(itemType).itemNum(player, itemPack, cfgId);
        return num >= needNum;
    }

    public void add(Player player, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        add(player, PackType.BAG, items, optReason, logs);
    }

    public void add(Player player, PackType packType, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        add(player, player.itemPack(packType), items, optReason, logs);
    }

    public void add(Player player, ItemPack itemPack, Collection<ItemCfg> items, OptReason optReason, String... logs) {

        for (ItemCfg itemCfg : items) {
            add(player, itemPack, itemCfg, optReason, logs);
        }

    }

    public void add(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        int cfgId = itemCfg.getCfgId();
        ItemType itemType = ItemType.as(cfgId);

        itemAction
                .changeAction(itemType)
                .add(player, itemPack, itemCfg, optReason, logs);
    }

    public void remove(Player player, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        remove(player, PackType.BAG, items, optReason, logs);
    }

    public void remove(Player player, PackType packType, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        remove(player, player.itemPack(packType), items, optReason, logs);
    }

    public void remove(Player player, ItemPack itemPack, Collection<ItemCfg> items, OptReason optReason, String... logs) {

        for (ItemCfg itemCfg : items) {
            remove(player, itemPack, itemCfg, optReason, logs);
        }

    }

    public void remove(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        int cfgId = itemCfg.getCfgId();
        ItemType itemType = ItemType.as(cfgId);

        itemAction
                .changeAction(itemType)
                .remove(player, itemPack, itemCfg, optReason, logs);
    }
}
