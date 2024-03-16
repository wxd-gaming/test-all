package org.wxd.mmo.core.cfg.table;


import lombok.Getter;
import org.wxd.boot.batis.store.DataRepository;
import org.wxd.boot.batis.struct.DbBean;
import org.wxd.mmo.core.cfg.bean.QItemshopVipRow;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_itemshop_vip - vip礼包
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-14 09:48:44
 **/
@Getter
public class QItemshopVipTable extends DbBean<QItemshopVipRow> implements Serializable {

    @Override public void initDb() {
        /*todo 实现一些数据分组*/

    }

    @Override public void checkDb(DataRepository dataRepository) {
        QVipTable dbBean = (QVipTable) dataRepository.getDbBean(QVipTable.class);
        for (QItemshopVipRow qItemshopVipRow : this.getModelList()) {
            if (dbBean.get(qItemshopVipRow.getShowViplv()) == null)
                throw new RuntimeException("缺少vip对应等级 " + qItemshopVipRow.getShowViplv());
        }
    }
}