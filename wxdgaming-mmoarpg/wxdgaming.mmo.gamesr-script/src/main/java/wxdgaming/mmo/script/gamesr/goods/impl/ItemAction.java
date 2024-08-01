package wxdgaming.mmo.script.gamesr.goods.impl;

import com.google.inject.Singleton;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.core.collection.Table;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.mmo.core.bean.bag.ItemCfg;
import wxdgaming.mmo.core.bean.bag.ItemGroup;
import wxdgaming.mmo.core.bean.bag.ItemType;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.goods.ItemModule;
import wxdgaming.mmo.script.gamesr.goods.impl.change.ItemChangeAction;
import wxdgaming.mmo.script.gamesr.goods.impl.create.ItemCreateAction;
import wxdgaming.mmo.script.gamesr.goods.impl.use.ItemUseAction;

import java.util.List;
import java.util.Optional;

/**
 * 道具创建
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class ItemAction implements IBeanInit {

    private final Table<ItemGroup, ItemType, ItemCreateAction> createActionTable = new Table<>();
    private final Table<ItemGroup, ItemType, ItemChangeAction> changeActionTable = new Table<>();
    private final Table<ItemGroup, ItemType, ItemUseAction> useActionTable = new Table<>();

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

        build.classWithSuper(ItemUseAction.class)
                .forEach(c -> {
                    ItemUseAction<? super Item> bean = (ItemUseAction) context.getInstance(c);
                    ItemGroup itemGroup = bean.itemGroup();
                    ItemType itemType = bean.itemType();
                    useActionTable.put(itemGroup, itemType, bean);
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

    public <R extends Item> ItemUseAction<R> useAction(ItemType itemType) {
        return useActionTable.opt(itemType.getItemGroup(), itemType)
                .or(() -> Optional.ofNullable(useActionTable.get(itemType.getItemGroup(), ItemType.None)))
                .or(() -> Optional.ofNullable(useActionTable.get(ItemGroup.None, ItemType.None)))
                .orElseThrow(() -> new RuntimeException("item use action 查找失败 " + itemType.toString()));
    }

}
