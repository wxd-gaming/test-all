package wxdgaming.mmo.script.gamesr.goods.impl.create;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import wxdgaming.boot.core.timer.MyClock;
import wxdgaming.mmo.core.bean.bag.ItemCfg;
import wxdgaming.mmo.core.bean.bag.ItemGroup;
import wxdgaming.mmo.core.bean.bag.ItemType;
import wxdgaming.mmo.core.bean.bag.goods.Item;
import wxdgaming.mmo.core.bean.data.UidSeed;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.gamesr.data.DataCenter;
import wxdgaming.mmo.script.gamesr.goods.impl.IAction;

import java.util.List;


/**
 * 道具创建
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class ItemCreateAction<T extends Item> implements IAction {

    @Inject DataCenter dataCenter;

    @Override public ItemGroup itemGroup() {
        return ItemGroup.None;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    public List<T> createItem(Player player, ItemCfg itemCfg) {
        T t = newInstance();
        initItem(t, itemCfg.getCfgId(), itemCfg.getNum(), itemCfg.isBind(), itemCfg.getExpire());
        return List.of(t);
    }

    protected T newInstance() {
        return (T) new Item();
    }

    protected void initItem(T t, int cfgId, long num, boolean bind, long expire) {
        if (num <= 0)
            throw new UnsupportedOperationException(cfgId + " 生成道具数量 " + num + " 异常");
        t
                .setUid(dataCenter.newUid(UidSeed.Type.Goods))
                .setCfgId(cfgId)
                .setNum(num)
                .setBind(bind);
        if (expire > 0) {
            t.setExpireTime(MyClock.millis() + expire);
        }
    }

}
