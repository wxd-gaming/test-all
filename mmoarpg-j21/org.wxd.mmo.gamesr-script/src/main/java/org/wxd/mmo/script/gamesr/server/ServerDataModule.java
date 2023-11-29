package org.wxd.mmo.script.gamesr.server;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.batis.mongodb.MongoDataHelper;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IShutdownBefore;
import org.wxd.boot.ioc.i.IShutdownEnd;
import org.wxd.boot.ioc.i.IStart;
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
@Resource
public class ServerDataModule implements IStart, IShutdownBefore, IShutdownEnd {

    @Resource MongoDataHelper mongoDataHelper;
    @Resource DataCenter dataCenter;

    @Override public void start(IocInjector iocInjector) throws Exception {
        List<ServerData> serverData = mongoDataHelper.queryEntities(ServerData.class);
        for (ServerData serverDatum : serverData) {
            dataCenter.getServerDataMap().put(serverDatum.getSid(), serverDatum);
        }
    }

    @Scheduled("*/5")
    public void save() throws Exception {
        dataCenter.getServerDataMap().values().forEach(mongoDataHelper.getBatchPool()::replace);
    }

    @Override public void shutdownBefore() throws Exception {
        log.info("准备关服，开始保存全局数据");
        dataCenter.getServerDataMap().values().forEach(mongoDataHelper::replace);
    }

    @Override public void shutdownEnd() throws Exception {
        log.info("停服完成，开始关闭数据库");
        mongoDataHelper.close();
    }


}
