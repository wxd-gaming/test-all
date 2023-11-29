package org.wxd.mmo.gamesr.bean.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.struct.DbColumn;
import org.wxd.boot.batis.struct.DbTable;
import org.wxd.boot.lang.save.ObjectSave;
import org.wxd.mmo.bean.data.UidSeed;

/**
 * 全局数据
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 19:56
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable
public class ServerData extends ObjectSave {

    @DbColumn(key = true)
    private int sid;
    /** 是否合并完成 */
    private boolean joined;
    /** 合并时间 */
    private long joinedTime;

    private UidSeed uidSeed = new UidSeed();

}