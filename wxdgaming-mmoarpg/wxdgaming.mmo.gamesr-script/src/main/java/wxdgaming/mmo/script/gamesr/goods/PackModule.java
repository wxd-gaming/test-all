package wxdgaming.mmo.script.gamesr.goods;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.mmo.core.bean.bag.*;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.goods.impl.ItemAction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 背包模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
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

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void remove(Player player, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        remove(player, PackType.BAG, items, optReason, logs);
    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void remove(Player player, PackType packType, Collection<ItemCfg> items, OptReason optReason, String... logs) {
        remove(player, player.itemPack(packType), items, optReason, logs);
    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void remove(Player player, ItemPack itemPack, Collection<ItemCfg> items, OptReason optReason, String... logs) {

        for (ItemCfg itemCfg : items) {
            remove(player, itemPack, itemCfg, optReason, logs);
        }

    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void remove(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        int cfgId = itemCfg.getCfgId();
        ItemType itemType = ItemType.as(cfgId);

        itemAction
                .changeAction(itemType)
                .remove(player, itemPack, itemCfg, optReason, logs);
    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void removeItem(Player player, Collection<Item> items, OptReason optReason, String... logs) {
        removeItem(player, PackType.BAG, items, optReason, logs);
    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void removeItem(Player player, PackType packType, Collection<Item> items, OptReason optReason, String... logs) {
        removeItem(player, player.itemPack(packType), items, optReason, logs);
    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void removeItem(Player player, ItemPack itemPack, Collection<Item> items, OptReason optReason, String... logs) {

        for (Item item : items) {
            removeItem(player, itemPack, item, optReason, logs);
        }

    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void removeItem(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        int cfgId = item.getCfgId();
        ItemType itemType = ItemType.as(cfgId);

        itemAction
                .changeAction(itemType)
                .remove(player, itemPack, item, optReason, logs);
    }

    /** 扣除道具 调用之前请使用 先判定是否足够 */
    public void removeItem(Player player, ItemPack itemPack, Item item, long changeNum, OptReason optReason, String... logs) {
        int cfgId = item.getCfgId();
        ItemType itemType = ItemType.as(cfgId);

        itemAction
                .changeAction(itemType)
                .remove(player, itemPack, item, changeNum, optReason, logs);
    }

    /** 使用道具 */
    public void use(Player player, int cfgId, long num, OptReason optReason, String... logs) {
        use(player, PackType.BAG, cfgId, num, optReason, logs);
    }

    public void use(Player player, PackType packType, int cfgId, long num, OptReason optReason, String... logs) {
        use(player, player.itemPack(packType), cfgId, num, optReason, logs);
    }

    /** 使用道具 */
    public void use(Player player, ItemPack itemPack, int cfgId, long num, OptReason optReason, String... logs) {
        ItemType itemType = ItemType.as(cfgId);
        itemAction
                .useAction(itemType)
                .use(player, itemPack, cfgId, num, optReason, logs);
    }

    /** 使用道具 */
    public void use(Player player, ItemPack itemPack, long itemUid, long num, OptReason optReason, String... logs) {
        itemPack.item(itemUid).ifPresentOrElse(
                item -> {
                    ItemType itemType = item.itemType();
                    itemAction
                            .useAction(itemType)
                            .use(player, itemPack, item, num, optReason, logs);
                },
                () -> {
                    log.warn(
                            "{} 使用道具 {} 未找到, {}-{}",
                            player,
                            itemUid,
                            optReason.getComment(),
                            String.join(",", logs)
                    );
                }
        );
    }

}
