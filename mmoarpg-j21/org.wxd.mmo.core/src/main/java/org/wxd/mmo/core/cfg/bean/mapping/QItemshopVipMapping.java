package org.wxd.mmo.core.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.struct.DataChecked;
import org.wxd.boot.batis.struct.DbColumn;
import org.wxd.boot.batis.struct.DbTable;
import org.wxd.boot.core.lang.ObjectBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * excel 构建 Vip礼包.xlsx - q_itemshop_vip - vip礼包
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable(mappedSuperclass = true, name = "q_itemshop_vip", comment = "Vip礼包.xlsx - q_itemshop_vip - vip礼包")
public abstract class QItemshopVipMapping extends ObjectBase implements DataChecked, Serializable {

    /** 主键id */
    @DbColumn(name = "id", key = true, comment = "主键id")
    protected int id;
    /** 商品内容 */
    @DbColumn(name = "shop_item", comment = "商品内容")
    protected int[][] shopItem;
    /** 服务器使用字段 */
    @DbColumn(name = "show_viplv", comment = "服务器使用字段")
    protected int showViplv;
    /** 是非类型 */
    @DbColumn(name = "conditions_viplv", comment = "是非类型")
    protected boolean conditionsViplv;
    /** 小数类型 */
    @DbColumn(name = "limit_num", comment = "小数类型")
    protected float limitNum;
    /** 售价 */
    @DbColumn(name = "price", comment = "售价")
    protected int[] price;

}
