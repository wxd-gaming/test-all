package wxdgaming.mmo.core.game.bean.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.struct.DbColumn;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.mmo.core.GameBase;
import wxdgaming.mmo.core.common.cache.user.AccountCache;
import wxdgaming.mmo.core.login.bean.user.Account;

/**
 * 玩家角色快照，排行榜，跨服都需要这些数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 20:19
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable
public class PlayerSnap extends GameBase {

    @DbColumn(index = true, comment = "服务器id")
    private int sId;
    @DbColumn(index = true, comment = "账号")
    private long accountId;
    @DbColumn(index = true, comment = "账号")
    private String accountName;
    /** 我们定义的平台 */
    @DbColumn(index = true)
    private String channel;
    /** 我们定义的平台--本次登录的平台 */
    @DbColumn(index = true)
    private String loginChannel;
    /** 角色名字 */
    @DbColumn(index = true)
    private String name;
    private int gmLv = 0;
    private int sex;
    private int job;
    private int lv;
    private long power;
    private long exp;
    private int vipLv;
    private long vipExp;

    public Account account() {
        return AccountCache.getInstance().get(this.getAccountName());
    }

    public String logName() {
        return "{" +
                "uid='" + getUid() + '\'' +
                ", name='" + name + '\'' +
                ", lv=" + lv +
                '}';
    }

}
