package wxdgaming.mmo.core.cfg.table;


import lombok.Getter;
import wxdgaming.boot.batis.store.JsonDataRepository;
import wxdgaming.boot.batis.store.ann.Keys;
import wxdgaming.boot.batis.struct.DbBean;
import wxdgaming.mmo.core.cfg.bean.QVipBean;
import wxdgaming.mmo.core.cfg.bean.mapping.QVipMapping;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_vip - vip等级
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-14 09:48:44
 **/
@Getter
@Keys({"id#exp"})
public class QVipTable extends DbBean<QVipBean, JsonDataRepository> implements Serializable {

    private int maxLv = 0;

    @Override public void initDb() {
        /*todo 实现一些数据分组*/
        maxLv = getModelList().stream().mapToInt(QVipMapping::getId).max().orElse(0);
    }

    @Override public void checkDb(JsonDataRepository dataRepository) {

    }
}