package wxdgaming.mmo.core.cfg.bean;


import lombok.Getter;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.mmo.core.cfg.bean.mapping.QItemshopVipMapping;

import java.io.Serializable;


/**
 * excel 构建 Vip.xlsx - q_itemshop_vip - vip礼包
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-03-14 09:48:44
 **/
@Getter
@DbTable(name = "q_itemshop_vip", comment = "Vip.xlsx - q_itemshop_vip - vip礼包")
public class QItemshopVipBean extends QItemshopVipMapping implements Serializable {

    @Override public void initAndCheck() throws Exception {
        /*todo 实现数据检测和初始化*/

    }

}
