package wxdgaming.mmo.script.gamesr.goods.impl.change;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.lang.LNum;
import wxdgaming.mmo.core.bean.bag.*;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.goods.impl.IAction;
import wxdgaming.mmo.script.gamesr.goods.impl.ItemAction;

import java.util.List;


/**
 * 添加道具进道具容器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-08 15:06
 **/
@Slf4j
@Singleton
public class ItemChangeAction<T extends Item> implements IAction {

    @Inject ItemAction itemAction;

    @Override public ItemGroup itemGroup() {
        return ItemGroup.None;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }


    /**
     * 添加奖励
     *
     * @param player    玩家
     * @param itemPack  背包
     * @param itemCfg   要添加的道具配置，例如任务奖励
     * @param optReason 操作来源
     * @param logs      日志项
     */
    public final void add(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        List<Item> items = itemAction.createItem(player, itemCfg);
        for (Item item : items) {
            add(player, itemPack, item, optReason, logs);
        }
    }

    /**
     * 添加奖励
     *
     * @param player    玩家
     * @param itemPack  背包
     * @param item      要添加的道具
     * @param optReason 操作来源
     * @param logs      日志项
     */
    public final void add(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        final long oldNum = itemNum(player, itemPack, item.getCfgId());
        add0(player, itemPack, item, optReason, logs);
        log.info("{} {} 获得：{}-{}({}), old：{}, change：{}, now：{}, 原因：{} - {}",
                player,
                itemPack.getPackType().getComment(),
                item.getUid(),
                item.getCfgId(),
                item.itemType().getComment(),
                oldNum,
                item.getNum(),
                itemNum(player, itemPack, item.getCfgId()),
                optReason.getComment(),
                String.join(",", logs)
        );
    }

    protected void add0(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        itemPack.getItems().put(item.getUid(), item);
    }

    public final void remove(Player player, ItemPack itemPack, ItemCfg itemCfg, OptReason optReason, String... logs) {
        final LNum itemCfgNum = new LNum(itemCfg.getNum());
        if (itemCfgNum.getNum() <= 0)
            throw new UnsupportedOperationException(itemCfg.getCfgId() + " 扣除道具 数量 " + itemCfgNum + " 异常");

        if (itemCfg.getUid() > 0) {
            Item item = itemPack.getItems().get(itemCfg.getUid());
            if (item != null) {
                remove(player, itemPack, item, itemCfgNum, optReason, logs);
            }
        }

        if (itemCfgNum.getNum() > 0) {
            List<Item> collect = itemPack.stream(itemCfg.getCfgId()).toList();
            for (Item item : collect) {
                remove(player, itemPack, item, itemCfgNum, optReason, logs);
                if (itemCfgNum.getNum() <= 0) break;
            }
        }
    }

    public final void remove(Player player, ItemPack itemPack, Item item, OptReason optReason, String... logs) {
        remove(player, itemPack, item, item.getNum(), optReason, logs);
    }

    public final void remove(Player player, ItemPack itemPack, Item item, long changeNum, OptReason optReason, String... logs) {
        final LNum lNum = new LNum(changeNum);
        remove(player, itemPack, item, lNum, optReason, logs);
    }

    public final void remove(Player player, ItemPack itemPack, Item item, LNum changeNum, OptReason optReason, String... logs) {
        final long oldNum = itemNum(player, itemPack, item.getCfgId());
        final long needChangeNum = changeNum.getNum();
        remove0(player, itemPack, item, changeNum, optReason, logs);
        log.info("{} {} 扣除：{}-{}({}), old：{}, change：-{}, now：{}, 原因：{}-{}",
                player,
                itemPack.getPackType().getComment(),
                item.getUid(),
                item.getCfgId(),
                item.itemType().getComment(),
                oldNum,
                needChangeNum - changeNum.getNum(),
                itemNum(player, itemPack, item.getCfgId()),
                optReason.getComment(),
                String.join(",", logs)
        );
    }

    protected void remove0(Player player, ItemPack itemPack, Item item, LNum itemCfgNum, OptReason optReason, String... logs) {
        if (item.getNum() > itemCfgNum.getNum()) {
            /*扣除部分道具*/
            item.sub(itemCfgNum.getNum(), 0L);
            itemCfgNum.setNum(0);
        } else {
            itemCfgNum.sub(item.getNum());
            /*这里是需要扣除全部这个时候不去重设数量直接删除*/
            Item remove = itemPack.getItems().remove(item.getUid());
            if (remove == null) throw new RuntimeException("移除道具异常：" + item.getUid());
        }
    }


    public long itemNum(Player player, PackType packType, int cfg) {
        return itemNum(player, player.getItemPackMap().get(packType), cfg);
    }

    public long itemNum(Player player, ItemPack itemPack, int cfg) {
        return itemPack.stream(cfg).mapToLong(LNum::getNum).sum();
    }

}
