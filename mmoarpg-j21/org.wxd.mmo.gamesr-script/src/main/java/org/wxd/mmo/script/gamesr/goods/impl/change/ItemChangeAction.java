package org.wxd.mmo.script.gamesr.goods.impl.change;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.gamesr.bean.bag.*;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;
import org.wxd.mmo.script.gamesr.goods.impl.ItemAction;


/**
 * 添加道具进道具容器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 15:06
 **/
@Slf4j
@Singleton
public class ItemChangeAction<T extends Item> implements IAction, IChange {

    @Inject ItemAction itemAction;

    @Override public ItemGroup itemGroup() {
        return ItemGroup.None;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    @Override public final void add(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        add0(player, itemPack, itemCfg, optReason, logs);
        log.info("{} {} 获得：{}-{}({}), 变更：{}, 现有数量：{}, 原因：{}{}",
                player,
                itemPack.getPackType().getComment(),
                itemCfg.getUid(),
                itemCfg.getCfgId(),
                "道具",
                itemCfg.getNum(),
                itemNum(player, itemPack, itemCfg.getCfgId()),
                optReason.getComment(),
                String.join(",", logs)
        );
    }

    protected void add0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        Item item = itemAction.createItem(player, itemCfg);
        add(player, itemPack, item, optReason, logs);
    }

    @Override public final void add(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        add0(player, itemPack, item, optReason, logs);
    }

    public void add0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        itemPack.getItems().put(item.getUid(), item);
    }

    @Override public void remove(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        remove0(player, itemPack, itemCfg, optReason, logs);
        log.info("{} {} 扣除：{}-{}({}), 变更：{}, 现有数量：{}, 原因：{}{}",
                player,
                itemPack.getPackType().getComment(),
                itemCfg.getUid(),
                itemCfg.getCfgId(),
                "道具",
                itemCfg.getNum(),
                itemNum(player, itemPack, itemCfg.getCfgId()),
                optReason.getComment(),
                String.join(",", logs)
        );
    }

    @Override public void remove(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        itemPack.getItems().remove(item.getUid());
    }

    protected void remove0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {

        long itemCfgNum = itemCfg.getNum();
        Item item = itemPack.getItems().get(itemCfg.getUid());
        if (item != null) {
            if (item.getNum() >= itemCfgNum) {
                /*扣除部分道具*/
                itemCfgNum = 0;
                item.sub(itemCfgNum, 0L);
            } else {
                itemCfgNum = itemCfgNum - item.getNum();
                /*这里是需要扣除全部这个时候不去重设数量直接删除*/
                itemPack.getItems().remove(itemCfg.getUid());
            }
        }

        if (itemCfgNum > 0) {

        }

    }

    protected long itemNum(Player player, PackType packType, int cfg) {
        return itemNum(player, player.getItemPackMap().get(packType), cfg);
    }

    protected long itemNum(Player player, ItemPack itemPack, int cfg) {
        return itemPack.itemNum(cfg);
    }

}
