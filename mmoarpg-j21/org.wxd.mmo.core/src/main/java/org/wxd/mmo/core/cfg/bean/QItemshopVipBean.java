package org.wxd.mmo.core.cfg.bean;


import lombok.Getter;
import org.wxd.boot.batis.struct.DbTable;
import org.wxd.mmo.core.cfg.bean.mapping.QItemshopVipMapping;

import java.io.Serializable;


/**
 * excel 构建 Vip礼包.xlsx - q_itemshop_vip - vip礼包
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-02 14:58:16
 **/
@Getter
@DbTable(name = "q_itemshop_vip", comment = "Vip礼包.xlsx - q_itemshop_vip - vip礼包")
public class QItemshopVipBean extends QItemshopVipMapping implements Serializable {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
