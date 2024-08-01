package wxdgaming.mmo.core.cfg.table;


import lombok.Getter;
import wxdgaming.boot.batis.store.JsonDataRepository;
import wxdgaming.boot.batis.struct.DbBean;
import wxdgaming.mmo.core.cfg.bean.QItemshopVipBean;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_itemshop_vip - vip礼包
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-14 09:48:44
 **/
@Getter
public class QItemshopVipTable extends DbBean<QItemshopVipBean, JsonDataRepository> implements Serializable {

    @Override public void initDb() {
        /*todo 实现一些数据分组*/

    }

    @Override public void checkDb(JsonDataRepository dataRepository) {
        QVipTable dbBean = dataRepository.getDbBean(QVipTable.class);
        for (QItemshopVipBean qItemshopVipBean : this.getModelList()) {
            if (dbBean.get(qItemshopVipBean.getShowViplv()) == null)
                throw new RuntimeException("缺少vip对应等级 " + qItemshopVipBean.getShowViplv());
        }
    }
}