package wxdgaming.mmo.core.bean.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.save.ObjectSave;
import wxdgaming.boot.core.lang.bit.BitUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * id生成器种子
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 16:08
 **/
@Setter
@Getter
@Accessors(chain = true)
public class UidSeed extends ObjectSave {

    public enum Type {
        /** 游戏角色 */
        Player,
        /** 公会 */
        Guild,
        /** 道具 */
        Goods,
        /** 邮件 */
        Mail,
        /** 杂项 */
        Other,
    }

    private ConcurrentHashMap<Type, Long> seedGroupMap = new ConcurrentHashMap<>();

    /** 创建唯一id */
    public long newUid(Type type, int hexId) {
        long merge = seedGroupMap.merge(type, 1L, Math::addExact);
        return BitUtil.merge64(hexId, merge);
    }

}
