package wxdgaming.mmo.core.login.bean.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.enums.ColumnType;
import wxdgaming.boot.batis.save.ObjectSave;
import wxdgaming.boot.batis.struct.DbColumn;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.mmo.core.bean.type.Platforms;
import wxdgaming.mmo.core.bean.type.SdkType;

/**
 * 玩家账号
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 15:06
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable(name = "account")
public class Account extends ObjectSave {

    @DbColumn(key = true)
    private long uid;
    /** 我们自己生成的账号 */
    @DbColumn(index = true)
    private String accountName;
    private String token;
    /** 平台传递的账号 */
    @DbColumn(index = true)
    private String channelAccountName;
    /** 我们定义的平台 */
    @DbColumn(index = true)
    private String channel;
    /** 我们定义的平台--本次登录的平台 */
    @DbColumn(index = true)
    private String loginChannel;
    @DbColumn(index = true)
    private long createTime;
    @DbColumn(index = true)
    private long lastLoginTime;
    @DbColumn(index = true, columnType = ColumnType.Varchar, length = 64)
    private Platforms platforms;
    @DbColumn(index = true, columnType = ColumnType.Varchar, length = 64)
    private SdkType sdkType;

    public String logName() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("uid='").append(getUid()).append('\'');
        sb.append(", accountName='").append(accountName).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
