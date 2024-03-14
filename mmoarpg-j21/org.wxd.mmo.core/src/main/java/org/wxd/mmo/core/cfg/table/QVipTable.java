package org.wxd.mmo.core.cfg.table;


import lombok.Getter;
import org.wxd.boot.batis.store.DataRepository;
import org.wxd.boot.batis.struct.DbBean;
import org.wxd.mmo.core.cfg.bean.QVipRow;
import org.wxd.mmo.core.cfg.bean.mapping.QVipMapping;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_vip - vip等级
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-14 09:48:44
 **/
@Getter
public class QVipTable extends DbBean<QVipRow> implements Serializable {

    private int maxLv = 0;

    @Override public void initDb() {
        /*todo 实现一些数据分组*/
        maxLv = getModelList().stream().mapToInt(QVipMapping::getId).max().orElse(0);
    }

    @Override public void checkDb(DataRepository dataRepository) {
    }
}