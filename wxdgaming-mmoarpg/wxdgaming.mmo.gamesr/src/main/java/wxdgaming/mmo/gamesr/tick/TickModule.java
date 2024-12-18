package wxdgaming.mmo.gamesr.tick;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import wxdgaming.boot.core.threading.ThreadInfo;
import wxdgaming.boot.core.timer.ann.Scheduled;
import wxdgaming.boot.starter.AppContext;
import wxdgaming.mmo.core.bean.config.ServerConfig;

/**
 * 心跳模块
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-27 21:06
 **/
@Singleton
public class TickModule {

    @Inject ServerConfig serverConfig;

    @Scheduled("*")
    @ThreadInfo(vt = true)
    public void serverSecondTick() {
        AppContext.context().forEachBean(IServerSecondTick.class, tick -> {
            tick.onServerSecond(serverConfig);
        });
    }

    @Scheduled("0 *")
    @ThreadInfo(vt = true)
    public void serverMinuteTick() {
        AppContext.context().forEachBean(IServerMinuteTick.class, tick -> {
            tick.onServerMinute(serverConfig);
        });
    }

    @Scheduled("0 0 *")
    @ThreadInfo(vt = true)
    public void serverHourTick() {
        AppContext.context().forEachBean(IServerHourTick.class, tick -> {
            tick.onServerHour(serverConfig);
        });
    }

    @Scheduled("0 0 0")
    @ThreadInfo(vt = true)
    public void serverDayTick() {
        AppContext.context().forEachBean(IServerDayTick.class, tick -> {
            tick.onServerDay(serverConfig);
        });
    }

}
