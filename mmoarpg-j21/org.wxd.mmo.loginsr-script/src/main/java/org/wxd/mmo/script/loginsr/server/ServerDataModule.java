package org.wxd.mmo.script.loginsr.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.core.timer.ann.Scheduled;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.i.IShutdownBefore;
import org.wxd.boot.starter.i.IShutdownEnd;
import org.wxd.boot.starter.i.IStart;
import org.wxd.mmo.loginsr.data.DataCenter;


/**
 * 全服数据模型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:24
 **/
@Slf4j
@Singleton
public class ServerDataModule implements IStart, IShutdownBefore, IShutdownEnd {

    @Inject DataCenter dataCenter;

    @Override public void start(IocContext iocContext) throws Exception {
    }

    @Scheduled("*/5")
    public void save() throws Exception {
        dataCenter.getLoginMongoService().getBatchPool().replace(dataCenter.getServerData());
    }

    @Override public void shutdownBefore() throws Exception {
        log.info("准备关服，开始保存全局数据");
        dataCenter.getLoginMongoService().replace(dataCenter.getServerData());
    }

    @Override public void shutdownEnd() throws Exception {
        log.info("停服完成，开始关闭数据库");
        dataCenter.getLoginMongoService().close();
    }


}
