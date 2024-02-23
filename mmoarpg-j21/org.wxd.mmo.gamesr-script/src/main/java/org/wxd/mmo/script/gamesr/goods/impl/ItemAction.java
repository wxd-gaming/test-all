package org.wxd.mmo.script.gamesr.goods.impl;

import com.google.inject.Singleton;
import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.core.collection.Table;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.bean.bag.ItemCfg;
import org.wxd.mmo.core.bean.bag.ItemGroup;
import org.wxd.mmo.core.bean.bag.ItemType;
import org.wxd.mmo.core.bean.bag.goods.Item;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.ItemModule;
import org.wxd.mmo.script.gamesr.goods.impl.change.ItemChangeAction;
import org.wxd.mmo.script.gamesr.goods.impl.create.ItemCreateAction;

import java.util.List;
import java.util.Optional;

/**
 * 道具创建
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class ItemAction implements IBeanInit {

    private final Table<ItemGroup, ItemType, ItemCreateAction> createActionTable = new Table<>();
    private final Table<ItemGroup, ItemType, ItemChangeAction> changeActionTable = new Table<>();

    @Override public void beanInit(IocContext context) throws Exception {
        ReflectContext.Builder reflectBuilder = ReflectContext.Builder.of(ItemModule.class.getClassLoader(), ItemModule.class.getPackageName());
        ReflectContext build = reflectBuilder.build();
        build.classWithSuper(ItemCreateAction.class)
                .forEach(c -> {
                    ItemCreateAction<? super Item> bean = (ItemCreateAction) context.getInstance(c);
                    ItemGroup itemGroup = bean.itemGroup();
                    ItemType itemType = bean.itemType();
                    createActionTable.put(itemGroup, itemType, bean);
                });

        build.classWithSuper(ItemChangeAction.class)
                .forEach(c -> {
                    ItemChangeAction<? super Item> bean = (ItemChangeAction) context.getInstance(c);
                    ItemGroup itemGroup = bean.itemGroup();
                    ItemType itemType = bean.itemType();
                    changeActionTable.put(itemGroup, itemType, bean);
                });
    }

    public final <R extends Item> List<R> createItem(Player player, ItemCfg itemCfg) {
        int cfgId = itemCfg.getCfgId();
        ItemType itemType = ItemType.as(cfgId);
        ItemCreateAction<R> action = createAction(itemType);
        return action.createItem(player, itemCfg);
    }

    public <R extends Item> ItemCreateAction<R> createAction(ItemType itemType) {
        return createActionTable.opt(itemType.getItemGroup(), itemType)
                .or(() -> Optional.ofNullable(createActionTable.get(itemType.getItemGroup(), ItemType.None)))
                .or(() -> Optional.ofNullable(createActionTable.get(ItemGroup.None, ItemType.None)))
                .orElseThrow(() -> new RuntimeException("item change action 查找失败 " + itemType.toString()));
    }

    public <R extends Item> ItemChangeAction<R> changeAction(ItemType itemType) {
        return changeActionTable.opt(itemType.getItemGroup(), itemType)
                .or(() -> Optional.ofNullable(changeActionTable.get(itemType.getItemGroup(), ItemType.None)))
                .or(() -> Optional.ofNullable(changeActionTable.get(ItemGroup.None, ItemType.None)))
                .orElseThrow(() -> new RuntimeException("item change action 查找失败 " + itemType.toString()));
    }

}
