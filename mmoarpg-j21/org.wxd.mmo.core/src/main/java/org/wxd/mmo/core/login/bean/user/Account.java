package org.wxd.mmo.core.login.bean.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.save.ObjectSave;
import org.wxd.boot.batis.struct.DbColumn;
import org.wxd.boot.batis.struct.DbTable;
import org.wxd.mmo.core.bean.type.Platforms;
import org.wxd.mmo.core.bean.type.SdkType;

/**
 * 玩家账号
 *
 * @author: Troy.Chen(無心道, 15388152619)
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
    @DbColumn(index = true)
    private Platforms platforms;
    @DbColumn(index = true)
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
