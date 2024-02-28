package org.wxd.mmo.gamesr.tick;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.wxd.boot.core.threading.Async;
import org.wxd.boot.core.timer.ann.Scheduled;
import org.wxd.boot.starter.Starter;
import org.wxd.mmo.core.struct.ServerInfo;

/**
 * 心跳模块
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-27 21:06
 **/
@Singleton
public class TickModule {

    @Inject ServerInfo serverInfo;

    @Scheduled("*")
    @Async(vt = true)
    public void serverSecondTick() {
        Starter.curIocInjector().forEachBean(IServerSecondTick.class, tick -> {
            tick.onServerSecond(serverInfo);
        });
    }

    @Scheduled("0 *")
    @Async(vt = true)
    public void serverMinuteTick() {
        Starter.curIocInjector().forEachBean(IServerMinuteTick.class, tick -> {
            tick.onServerMinute(serverInfo);
        });
    }

    @Scheduled("0 0 *")
    @Async(vt = true)
    public void serverHourTick() {
        Starter.curIocInjector().forEachBean(IServerHourTick.class, tick -> {
            tick.onServerHour(serverInfo);
        });
    }

    @Scheduled("0 0 0")
    @Async(vt = true)
    public void serverDayTick() {
        Starter.curIocInjector().forEachBean(IServerDayTick.class, tick -> {
            tick.onServerDay(serverInfo);
        });
    }

}
