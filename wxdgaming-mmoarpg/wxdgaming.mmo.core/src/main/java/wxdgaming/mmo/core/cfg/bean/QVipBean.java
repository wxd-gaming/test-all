package wxdgaming.mmo.core.cfg.bean;


import lombok.Getter;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.mmo.core.cfg.bean.mapping.QVipMapping;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_vip - vip等级
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-03-14 09:48:44
 **/
@Getter
@DbTable(name = "q_vip", comment = "Vip.xlsx - q_vip - vip等级")
public class QVipBean extends QVipMapping implements Serializable {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
