package org.wxd.mmo.gamesr.bean.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.batis.struct.DbTable;
import org.wxd.boot.core.lang.LNum;
import org.wxd.mmo.GameBase;
import org.wxd.mmo.game.bean.user.PlayerSnap;
import org.wxd.mmo.game.cache.user.PlayerSnapCache;
import org.wxd.mmo.gamesr.bean.bag.ItemPack;
import org.wxd.mmo.gamesr.bean.bag.PackType;
import org.wxd.mmo.gamesr.bean.mail.MailPackage;
import org.wxd.mmo.gamesr.cache.mail.MailPackageCache;
import org.wxd.mmo.login.bean.user.Account;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户快照
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:19
 **/
@Getter
@Setter
@Accessors(chain = true)
@DbTable
public class Player extends GameBase {

    /** 道具容器 */
    private final ConcurrentHashMap<PackType, ItemPack> itemPackMap = new ConcurrentHashMap<>();
    /** 在线时间，秒 */
    private LNum onlineSce = new LNum();

    /** 账号 */
    public Account account() {
        return playerSnap().account();
    }

    /** 玩家的快照 */
    public PlayerSnap playerSnap() {
        return PlayerSnapCache.getInstance().cache(this.getUid());
    }

    /** 玩家的邮件背包 */
    public MailPackage mailPackage() {
        return MailPackageCache.getInstance().cache(this.getUid());
    }

    public boolean online() {
        return false;
    }

}
