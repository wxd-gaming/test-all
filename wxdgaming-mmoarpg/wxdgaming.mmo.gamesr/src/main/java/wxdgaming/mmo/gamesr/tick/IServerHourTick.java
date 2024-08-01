package wxdgaming.mmo.gamesr.tick;

import wxdgaming.mmo.core.bean.config.ServerConfig;

/**
 * 服务器心跳
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-02-27 21:09
 **/
public interface IServerHourTick {

    void onServerHour(ServerConfig serverInfo);

}
