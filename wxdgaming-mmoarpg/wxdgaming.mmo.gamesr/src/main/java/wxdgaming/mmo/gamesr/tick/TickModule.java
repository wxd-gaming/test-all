package wxdgaming.mmo.gamesr.tick;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import wxdgaming.boot.core.threading.Async;
import wxdgaming.boot.core.timer.ann.Scheduled;
import wxdgaming.boot.starter.Starter;
import wxdgaming.mmo.core.bean.config.ServerConfig;

/**
 * 心跳模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-27 21:06
 **/
@Singleton
public class TickModule {

    @Inject ServerConfig serverConfig;

    @Scheduled("*")
    @Async(vt = true)
    public void serverSecondTick() {
        Starter.curIocInjector().forEachBean(IServerSecondTick.class, tick -> {
            tick.onServerSecond(serverConfig);
        });
    }

    @Scheduled("0 *")
    @Async(vt = true)
    public void serverMinuteTick() {
        Starter.curIocInjector().forEachBean(IServerMinuteTick.class, tick -> {
            tick.onServerMinute(serverConfig);
        });
    }

    @Scheduled("0 0 *")
    @Async(vt = true)
    public void serverHourTick() {
        Starter.curIocInjector().forEachBean(IServerHourTick.class, tick -> {
            tick.onServerHour(serverConfig);
        });
    }

    @Scheduled("0 0 0")
    @Async(vt = true)
    public void serverDayTick() {
        Starter.curIocInjector().forEachBean(IServerDayTick.class, tick -> {
            tick.onServerDay(serverConfig);
        });
    }

}
