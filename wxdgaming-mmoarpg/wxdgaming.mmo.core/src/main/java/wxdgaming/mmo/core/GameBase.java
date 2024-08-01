package wxdgaming.mmo.core;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.save.ObjectSave;
import wxdgaming.boot.batis.struct.DbColumn;

/**
 * 基类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-03 14:30
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GameBase extends ObjectSave {

    /** 唯一id */
    @DbColumn(key = true)
    protected long uid;

    public GameBase() {
    }

    public GameBase(long uid) {
        this.uid = uid;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("{");
        sb.append("uid=").append(uid);
        sb.append('}');
        return sb.toString();
    }
}
