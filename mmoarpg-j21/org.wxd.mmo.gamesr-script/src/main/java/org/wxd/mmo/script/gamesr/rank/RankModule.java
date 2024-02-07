package org.wxd.mmo.script.gamesr.rank;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.lang.rank.RankMap;
import org.wxd.boot.core.lang.rank.RankScore;
import org.wxd.boot.starter.batis.MongoService;
import org.wxd.boot.starter.i.IShutdown;
import org.wxd.mmo.game.cache.user.PlayerSnapCache;
import org.wxd.mmo.gamesr.bean.rank.RankType;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.gamesr.data.DataCenter;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 排行榜模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-07 20:21
 **/
@Slf4j
@Singleton
public class RankModule implements IShutdown {

    @Inject MongoService mongoService;
    @Inject DataCenter dataCenter;
    @Inject PlayerSnapCache playerSnapCache;

    public RankMap<Long, ? extends RankScore<Long>> rankMap(RankType rankType) {
        return dataCenter.getRanks().computeIfAbsent(rankType, l -> new RankMap<>());
    }

    public void update(Player player) {
        {
            RankMap<Long, ? extends RankScore<Long>> levelRankMap = rankMap(RankType.Level);
            levelRankMap.setScore(player.getUid(), player.getLevel());
            Collection<? extends RankScore<Long>> levelRange = levelRankMap.getRange(0, 10);
            if (levelRange.size() == 10) {
                log.info("等级排行榜：\n[\n{}\n]", levelRange.stream().map(r -> r.getUid() + " - " + playerSnapCache.cache(r.getUid()).getName() + " - " + r.scoreLongValue()).collect(Collectors.joining("\n")));
            }
        }
        {
            RankMap<Long, ? extends RankScore<Long>> powerRankMap = rankMap(RankType.Power);
            powerRankMap.setScore(player.getUid(), player.getPower());

            Collection<? extends RankScore<Long>> powerRange = powerRankMap.getRange(0, 10);
            if (powerRange.size() == 10) {
                log.info("战力排行榜：\n[\n{}\n]", powerRange.stream().map(r -> r.getUid() + " - " + playerSnapCache.cache(r.getUid()).getName() + " - " + r.scoreLongValue()).collect(Collectors.joining("\n")));
            }
        }
    }

    @Override public void shutdown() throws Exception {

    }

}
