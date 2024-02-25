package org.wxd.mmo.script.gamesr.goods.impl.create;

import com.google.inject.Singleton;
import org.wxd.mmo.core.bean.bag.ItemCfg;
import org.wxd.mmo.core.bean.bag.ItemGroup;
import org.wxd.mmo.core.bean.bag.ItemType;
import org.wxd.mmo.core.bean.bag.goods.Currency;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.goods.impl.IAction;

import java.util.List;


/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-07 20:24
 **/
@Singleton
public class CurrencyCreateAction extends ItemCreateAction<Currency> implements IAction {

    @Override public ItemGroup itemGroup() {
        return ItemGroup.CURRENCY;
    }

    @Override public ItemType itemType() {
        return super.itemType();
    }

    @Override public List<Currency> createItem(Player player, ItemCfg itemCfg) {
        Currency currency = newInstance();
        initItem(currency, itemCfg.getCfgId(), itemCfg.getNum(), itemCfg.isBind(), itemCfg.getExpire());
        return List.of(currency);
    }

    @Override protected Currency newInstance() {
        return new Currency();
    }

    @Override protected void initItem(Currency currency, int cfgId, long num, boolean bind, long expire) {
        super.initItem(currency, cfgId, num, bind, expire);
        currency.setUid(cfgId);
    }
}
