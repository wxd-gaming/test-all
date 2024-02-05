package org.wxd.mmo.script.gamesr.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.system.MarkTimer;
import org.wxd.boot.core.timer.ann.Scheduled;
import org.wxd.mmo.bean.config.ServerConfig;
import org.wxd.mmo.bean.data.UidSeed;
import org.wxd.mmo.common.cache.user.AccountCache;
import org.wxd.mmo.game.bean.user.PlayerSnap;
import org.wxd.mmo.game.cache.user.PlayerSnapCache;
import org.wxd.mmo.gamesr.bean.bag.ItemPack;
import org.wxd.mmo.gamesr.bean.bag.PackType;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.gamesr.cache.user.PlayerCache;
import org.wxd.mmo.gamesr.data.DataCenter;


/**
 * 玩家管理
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-10 15:41
 **/
@Slf4j
@Singleton
public class UserModule {

    @Inject ServerConfig serverConfig;
    @Inject AccountCache accountCache;
    @Inject PlayerCache playerCache;
    @Inject PlayerSnapCache playerSnapCache;
    @Inject DataCenter dataCenter;

    Player userFirst = null;

    @Scheduled("*/5")
    public void test() {
        {
            MarkTimer markTimer = MarkTimer.build();
            log.info("当前角色总算：{}, 缓存数：{} {}", playerCache.accountDbSize(), playerCache.cacheSize(), markTimer.execTime2String());
            markTimer.clear();
            if (userFirst != null) {
                getUserInfo(userFirst.getUid());
            }
        }
        {
            MarkTimer markTimer = MarkTimer.build();
            int f = 10;
            for (int i = 0; i < f; i++) {

                Player player = new Player();
                player.setUid(dataCenter.newUid(UidSeed.Type.Player));
                player.getItemPackMap().computeIfAbsent(PackType.BAG, b -> new ItemPack().setInitSize(100));
                player.getItemPackMap().computeIfAbsent(PackType.STORE, b -> new ItemPack().setInitSize(100));
                playerCache.addCache(player.getUid(), player);

                PlayerSnap playerSnap = new PlayerSnap();
                playerSnap.setUid(player.getUid());
                playerSnapCache.addCache(playerSnap.getUid(), playerSnap);

                if (userFirst == null) {
                    userFirst = player;
                }
            }
            log.info("创建 {} 条数据{}", f, markTimer.execTime2String());
        }
    }

    public Player getUserInfo(long uid) {
        MarkTimer markTimer = MarkTimer.build();
        Player Player = playerCache.cache(uid);
        log.info("读取一条数据{} {}", markTimer.execTime2String(), Player.toJson());
        log.info("读取一条数据{} {}", markTimer.execTime2String(), Player.toJsonWriteType());
        return Player;
    }

}
