package org.wxd.mmo.script.gamesr.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.batis.MongoService;
import org.wxd.boot.starter.i.IShutdownBefore;
import org.wxd.boot.starter.i.IShutdownEnd;
import org.wxd.boot.starter.i.IStart;
import org.wxd.boot.timer.ann.Scheduled;
import org.wxd.mmo.gamesr.bean.data.ServerData;
import org.wxd.mmo.gamesr.data.DataCenter;

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

    @Inject MongoService mongoService;
    @Inject DataCenter dataCenter;

    @Override public void start(IocContext iocInjector) throws Exception {
        List<ServerData> serverData = mongoService.queryEntities(ServerData.class);
        for (ServerData serverDatum : serverData) {
            dataCenter.getServerDataMap().put(serverDatum.getSid(), serverDatum);
        }
    }

    @Scheduled("*/5")
    public void save() throws Exception {
        dataCenter.getServerDataMap().values().forEach(mongoService.getBatchPool()::replace);
    }

    @Override public void shutdownBefore() throws Exception {
        log.info("准备关服，开始保存全局数据");
        dataCenter.getServerDataMap().values().forEach(mongoService::replace);
    }

    @Override public void shutdownEnd() throws Exception {
        log.info("停服完成，开始关闭数据库");
        mongoService.close();
    }


}
