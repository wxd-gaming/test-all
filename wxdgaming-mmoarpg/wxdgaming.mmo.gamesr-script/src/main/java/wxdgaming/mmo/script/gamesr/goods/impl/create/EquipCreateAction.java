package wxdgaming.mmo.script.gamesr.goods.impl.create;

import com.google.inject.Singleton;
import wxdgaming.boot.core.lang.RandomUtils;
import wxdgaming.mmo.core.bean.bag.ItemCfg;
import wxdgaming.mmo.core.bean.bag.ItemGroup;
import wxdgaming.mmo.core.bean.bag.ItemType;
import wxdgaming.mmo.core.bean.bag.goods.Equip;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.goods.impl.IAction;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class EquipCreateAction extends ItemCreateAction<Equip> implements IAction {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.EQUIP;
    }

    @Override public ItemType itemType() {
        return super.itemType();
    }

    @Override public List<Equip> createItem(Player player, ItemCfg itemCfg) {
        List<Equip> equips = new ArrayList<>();
        for (int i = 0; i < itemCfg.getNum(); i++) {
            Equip equip = newInstance();
            initItem(equip, itemCfg.getCfgId(), 1, itemCfg.isBind(), itemCfg.getExpire());
            equips.add(equip);
        }
        return List.copyOf(equips);
    }

    @Override protected Equip newInstance() {
        return new Equip();
    }

    @Override protected void initItem(Equip equip, int cfgId, long num, boolean bind, long expire) {
        super.initItem(equip, cfgId, num, bind, expire);
        /*模拟战斗力*/
        equip.setFightPower(RandomUtils.random(33, 99999));
    }

}
