package wxdgaming.mmo.gamesr.bean.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.batis.struct.DbTable;
import wxdgaming.boot.core.lang.LNum;
import wxdgaming.boot.net.SocketSession;
import wxdgaming.mmo.core.bean.bag.ItemPack;
import wxdgaming.mmo.core.bean.bag.PackType;
import wxdgaming.mmo.core.game.bean.fight.Fight;
import wxdgaming.mmo.core.game.bean.user.PlayerSnap;
import wxdgaming.mmo.core.game.cache.user.PlayerSnapCache;
import wxdgaming.mmo.core.login.bean.user.Account;
import wxdgaming.mmo.gamesr.bean.mail.MailPackage;
import wxdgaming.mmo.gamesr.bean.task.TaskPackage;
import wxdgaming.mmo.gamesr.cache.mail.MailPackageCache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户快照
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 20:19
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable
public class Player extends Fight {

    /** 道具容器 */
    private final ConcurrentHashMap<PackType, ItemPack> itemPackMap = new ConcurrentHashMap<>();
    /** 在线时间，秒 */
    private LNum onlineSce = new LNum();
    private TaskPackage taskPackage = new TaskPackage();

    private transient SocketSession socketSession;

    public Player() {
    }

    public Player(long uid) {
        super(uid);
    }

    /** 账号 */
    public Account account() {
        return playerSnap().account();
    }

    /** 玩家的快照 */
    public PlayerSnap playerSnap() {
        return PlayerSnapCache.getInstance().get(this.getUid());
    }

    /** 玩家的邮件背包 */
    public MailPackage mailPackage() {
        return MailPackageCache.getInstance().get(this.getUid());
    }

    public ItemPack itemPack(PackType packType) {
        return itemPackMap.get(packType);
    }

    public boolean online() {
        return false;
    }

}
