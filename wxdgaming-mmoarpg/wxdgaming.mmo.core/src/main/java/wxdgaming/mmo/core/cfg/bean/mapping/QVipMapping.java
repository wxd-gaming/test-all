package wxdgaming.mmo.core.cfg.bean.mapping;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.struct.DataChecked;
import wxdgaming.boot.batis.struct.DbColumn;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.boot.core.lang.ObjectBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * excel 构建 Vip.xlsx - q_vip - vip等级
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable(mappedSuperclass = true, name = "q_vip", comment = "Vip.xlsx - q_vip - vip等级")
public abstract class QVipMapping extends ObjectBase implements DataChecked, Serializable {

    /** vip等级 */
    @DbColumn(name = "id", key = true, comment = "vip等级")
    protected int id;
    /** vip升级所需要的经验 */
    @DbColumn(name = "exp", comment = "vip升级所需要的经验")
    protected int exp;
    /** vip名字 */
    @DbColumn(name = "vip_name", comment = "vip名字")
    protected String vipName;
    /** 解锁后获得奖励 */
    @DbColumn(name = "rewards", comment = "解锁后获得奖励")
    protected int[][] rewards;

}
