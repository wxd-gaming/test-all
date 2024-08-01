package wxdgaming.mmo.loginsr.bean.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.boot.core.lang.bit.BitUtil;
import wxdgaming.mmo.core.GameBase;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 数据库全局数据
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-04 20:23
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable
public class ServerData extends GameBase {

    private AtomicLong accountSeed = new AtomicLong();

    /** 创建唯一id */
    public long newAccountUid(int hexId) {
        long merge = accountSeed.incrementAndGet();
        return BitUtil.merge64(hexId, merge);
    }

    @Override public ServerData setUid(long uid) {
        super.setUid(uid);
        return this;
    }
}
