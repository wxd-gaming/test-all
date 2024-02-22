package org.wxd.mmo.script.gamesr.user;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.lang.RandomUtils;
import org.wxd.boot.core.str.StringUtil;
import org.wxd.boot.core.system.MarkTimer;
import org.wxd.boot.core.timer.ann.Scheduled;
import org.wxd.mmo.core.bean.config.ServerConfig;
import org.wxd.mmo.core.bean.data.UidSeed;
import org.wxd.mmo.core.common.cache.user.AccountCache;
import org.wxd.mmo.core.game.bean.user.PlayerSnap;
import org.wxd.mmo.core.game.cache.user.PlayerSnapCache;
import org.wxd.mmo.gamesr.bean.bag.*;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.gamesr.cache.user.PlayerCache;
import org.wxd.mmo.gamesr.data.DataCenter;
import org.wxd.mmo.script.gamesr.goods.PackModule;
import org.wxd.mmo.script.gamesr.rank.RankModule;

import java.util.List;


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
    @Inject PackModule packModule;
    @Inject RankModule rankModule;
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

                Player player = createPlayer();


                {
                    List<ItemCfg> build = List.of(
                            ItemCfg.builder().setCfgId(ItemType.Gold.getCode()).setNum(3).build(),
                            ItemCfg.builder().setCfgId(ItemType.Gold.getCode()).setNum(3).build(),

                            ItemCfg.builder().setCfgId(ItemType.Money.getCode()).setNum(1).build(),
                            ItemCfg.builder().setCfgId(ItemType.Money.getCode()).setNum(10).build(),

                            ItemCfg.builder().setCfgId(ItemType.Exp.getCode()).setNum(1000).build(),
                            ItemCfg.builder().setCfgId(ItemType.Exp.getCode()).setNum(100000).build(),

                            ItemCfg.builder().setCfgId(ItemType.Equip_Weapon.getCode()).setNum(1).build(),
                            ItemCfg.builder().setCfgId(ItemType.Equip_Weapon.getCode()).setNum(1).build(),

                            ItemCfg.builder().setCfgId(ItemType.Equip_Helmet.getCode()).setNum(1).build(),
                            ItemCfg.builder().setCfgId(ItemType.Equip_Helmet.getCode()).setNum(1).build()
                    );

                    packModule.add(player, build, OptReason.None, "GM测试");
                }
                {
                    List<ItemCfg> build = List.of(
                            ItemCfg.builder().setCfgId(ItemType.Gold.getCode()).setNum(1).build(),

                            ItemCfg.builder().setCfgId(ItemType.Money.getCode()).setNum(1).build(),

                            ItemCfg.builder().setCfgId(ItemType.Exp.getCode()).setNum(1000).build(),

                            ItemCfg.builder().setCfgId(ItemType.Equip_Weapon.getCode()).setNum(1).build(),

                            ItemCfg.builder().setCfgId(ItemType.Equip_Helmet.getCode()).setNum(1).build()
                    );
                    packModule.remove(player, build, OptReason.None, "GM测试");
                }
                {
                    rankModule.update(player);
                }
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

    public Player createPlayer() {
        Player player = new Player();
        player.setUid(dataCenter.newUid(UidSeed.Type.Player));

        player.setName(StringUtil.uuid32(12));
        player.setLevel(RandomUtils.random(10, 100));
        player.setFightPower(RandomUtils.random(10000, 10000000));

        player.getItemPackMap().computeIfAbsent(PackType.BAG, b -> new ItemPack(PackType.BAG, 100));
        player.getItemPackMap().computeIfAbsent(PackType.STORE, b -> new ItemPack(PackType.STORE, 100));
        playerCache.addCache(player.getUid(), player);

        PlayerSnap playerSnap = new PlayerSnap();
        playerSnap.setUid(player.getUid());
        playerSnap.setName(player.getName());
        playerSnap.setLv(player.getLevel());
        playerSnap.setPower(player.getFightPower());
        playerSnapCache.addCache(playerSnap.getUid(), playerSnap);
        return player;
    }

}
