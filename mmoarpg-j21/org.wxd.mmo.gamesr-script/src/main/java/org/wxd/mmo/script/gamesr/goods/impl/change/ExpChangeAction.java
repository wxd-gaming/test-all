package org.wxd.mmo.script.gamesr.goods.impl.change;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.mmo.gamesr.bean.bag.*;
import org.wxd.mmo.gamesr.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;


/**
 * 添加道具进道具容器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 15:06
 **/
@Slf4j
@Singleton
public class ExpChangeAction extends ItemChangeAction<Item> implements IAction, IChange {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.ITEM;
    }

    @Override public ItemType itemType() {
        return ItemType.Exp;
    }

    @Override public void add0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        itemPack.getMoneys().merge(itemCfg.getCfgId(), itemCfg.getNum(), Math::addExact);

        log.info("{} {} 获得：{}, 变更：{}, 现有数量：{}, 原因：{}{}",
                player,
                itemPack.getPackType().getComment(),
                itemCfg.getCfgId(),
                itemCfg.getNum(),
                itemNum(player, itemPack, itemCfg.getCfgId()),
                optReason.getComment(),
                String.join(",", logs)
        );

    }

    @Override public void remove0(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {

        itemPack.getMoneys().merge(itemCfg.getCfgId(), -itemCfg.getNum(), Math::addExact);

        log.info("{} {} 扣除：{}, 变更：{}, 现有数量：{}, 原因：{}{}",
                player,
                itemPack.getPackType().getComment(),
                itemCfg.getCfgId(),
                itemCfg.getNum(),
                itemNum(player, itemPack, itemCfg.getCfgId()),
                optReason.getComment(),
                String.join(",", logs)
        );

    }

    protected long itemNum(Player player, PackType packType, int cfg) {
        return itemNum(player, player.getItemPackMap().get(packType), cfg);
    }

    protected long itemNum(Player player, ItemPack itemPack, int cfg) {
        return 0;
    }

}
