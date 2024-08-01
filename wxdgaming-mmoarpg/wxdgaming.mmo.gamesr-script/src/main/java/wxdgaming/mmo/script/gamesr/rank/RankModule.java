package wxdgaming.mmo.script.gamesr.rank;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.lang.rank.RankMap;
import wxdgaming.boot.core.lang.rank.RankScore;
import wxdgaming.boot.starter.batis.MysqlService;
import wxdgaming.boot.starter.i.IShutdown;
import wxdgaming.mmo.core.bean.rank.RankType;
import wxdgaming.mmo.core.game.cache.user.PlayerSnapCache;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.gamesr.data.DataCenter;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 排行榜模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-07 20:21
 **/
@Slf4j
@Singleton
public class RankModule implements IShutdown {

    @Inject MysqlService gameDb;
    @Inject DataCenter dataCenter;
    @Inject PlayerSnapCache playerSnapCache;

    public RankMap<RankScore> rankMap(RankType rankType) {
        return dataCenter.getRanks().computeIfAbsent(rankType, l -> new RankMap<>());
    }

    public void update(Player player) {
        {
            RankMap<RankScore> levelRankMap = rankMap(RankType.Level);
            levelRankMap.setScore(player.getUid(), player.getLevel());
            Collection<RankScore> levelRange = levelRankMap.getRange(0, 10);
            if (levelRange.size() == 10) {
                log.info("等级排行榜：\n[\n{}\n]", levelRange.stream().map(r -> r.getUid() + " - " + playerSnapCache.get(r.uid2Long()).getName() + " - " + r.scoreLongValue()).collect(Collectors.joining("\n")));
            }
        }
        {
            RankMap<RankScore> powerRankMap = rankMap(RankType.Power);
            powerRankMap.setScore(player.getUid(), player.getFightPower());

            Collection<RankScore> powerRange = powerRankMap.getRange(0, 10);
            if (powerRange.size() == 10) {
                log.info("战力排行榜：\n[\n{}\n]", powerRange.stream().map(r -> r.getUid() + " - " + playerSnapCache.get(r.uid2Long()).getName() + " - " + r.scoreLongValue()).collect(Collectors.joining("\n")));
            }
        }
    }

    @Override public void shutdown() throws Exception {

    }

}
