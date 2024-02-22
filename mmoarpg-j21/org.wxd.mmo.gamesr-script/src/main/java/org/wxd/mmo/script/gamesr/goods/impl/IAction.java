package org.wxd.mmo.script.gamesr.goods.impl;

import org.wxd.mmo.core.bean.bag.ItemGroup;
import org.wxd.mmo.core.bean.bag.ItemType;

/**
 * 控制器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-08 16:09
 **/
public interface IAction {

    ItemGroup itemGroup();

    ItemType itemType();
}
