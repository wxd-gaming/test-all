package org.wxd.mmo.core.cfg.factory;


import lombok.Getter;
import org.wxd.boot.batis.struct.DbBean;
import org.wxd.mmo.core.cfg.bean.QItemshopVipBean;

import java.io.Serializable;


/**
 * excel 构建 Vip礼包.xlsx - q_itemshop_vip - vip礼包
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-02 14:58:16
 **/
@Getter
public class QItemshopVipFactory extends DbBean<QItemshopVipBean> implements Serializable {

    @Override public void initDb() {
        /*todo 实现一些数据分组*/

    }

}