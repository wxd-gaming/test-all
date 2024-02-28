package org.wxd.mmo.gamesr.tick;

import org.wxd.mmo.core.struct.ServerInfo;

/**
 * 服务器心跳
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-27 21:09
 **/
public interface IServerDayTick {

    void onServerDay(ServerInfo serverInfo);

}
