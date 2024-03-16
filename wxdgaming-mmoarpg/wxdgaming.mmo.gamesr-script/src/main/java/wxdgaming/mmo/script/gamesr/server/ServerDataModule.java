package wxdgaming.mmo.script.gamesr.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.timer.ann.Scheduled;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.batis.MysqlService;
import wxdgaming.boot.starter.i.IShutdownBefore;
import wxdgaming.boot.starter.i.IShutdownEnd;
import wxdgaming.boot.starter.i.IStart;
import wxdgaming.mmo.gamesr.bean.data.ServerData;
import wxdgaming.mmo.gamesr.data.DataCenter;

import java.util.List;

/**
 * 全服数据模型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:24
 **/
@Slf4j
@Singleton
public class ServerDataModule implements IStart, IShutdownBefore, IShutdownEnd {

    @Inject MysqlService gameDb;
    @Inject DataCenter dataCenter;

    @Override public void start(IocContext iocInjector) throws Exception {
        List<ServerData> serverData = gameDb.queryEntities(ServerData.class);
        for (ServerData serverDatum : serverData) {
            dataCenter.getServerDataMap().put(serverDatum.getSid(), serverDatum);
        }
    }

    @Scheduled("*/5")
    public void save() throws Exception {
        dataCenter.getServerDataMap().values().forEach(gameDb.getBatchPool()::replace);
    }

    @Override public void shutdownBefore() throws Exception {
        log.info("准备关服，开始保存全局数据");
        dataCenter.getServerDataMap().values().forEach(gameDb::replace);
    }

    @Override public void shutdownEnd() throws Exception {
        log.info("停服完成，开始关闭数据库");
        gameDb.close();
    }


}
