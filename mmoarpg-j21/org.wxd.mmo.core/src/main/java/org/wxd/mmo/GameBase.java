package org.wxd.mmo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.struct.DbColumn;
import org.wxd.boot.lang.save.ObjectSave;

/**
 * 基类
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 14:30
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GameBase extends ObjectSave {

    /** 唯一id */
    @DbColumn(key = true)
    private long uid;

    public GameBase() {
    }

    public GameBase(long uid) {
        this.uid = uid;
    }

}
