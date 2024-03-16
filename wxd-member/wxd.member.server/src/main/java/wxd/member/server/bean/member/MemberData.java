package wxd.member.server.bean.member;

import lombok.Getter;
import lombok.Setter;
import wxdgaming.boot.batis.save.ObjectSave;
import wxdgaming.boot.batis.struct.DbColumn;

/**
 * 会员信息
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-05-22 20:17
 **/
@Getter
@Setter
public class MemberData extends ObjectSave {
    
    /** 唯一id */
    @DbColumn(key = true, comment = "会员号")
    private long uid;
    @DbColumn(index = true, comment = "名字")
    private String name;
    /** 电话 */
    private String phone;
    /** 总充值金额 */
    private long totle;

}
