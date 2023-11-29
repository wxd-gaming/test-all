package org.wxd.mmo.script.gamesr.goods.impl.create;

import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.lang.RandomUtils;
import org.wxd.mmo.gamesr.bean.bag.ItemCfg;
import org.wxd.mmo.gamesr.bean.bag.ItemGroup;
import org.wxd.mmo.gamesr.bean.bag.ItemType;
import org.wxd.mmo.gamesr.bean.bag.goods.Equip;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;


/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Resource
public class EquipCreateAction extends ItemCreateAction<Equip> implements IAction {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.EQUIP;
    }

    @Override public ItemType itemType() {
        return ItemType.None;
    }

    @Override protected Equip newInstance() {
        return new Equip();
    }

    @Override protected void initItem(Player player, Equip equip, ItemCfg itemCfg) {
        super.initItem(player, equip, itemCfg);
        /*模拟战斗力*/
        equip.setFightPower(RandomUtils.random(33, 99999));
    }
}
