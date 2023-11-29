package org.wxd.mmo.script.loginsr.server;

import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IShutdownBefore;
import org.wxd.boot.ioc.i.IShutdownEnd;
import org.wxd.boot.ioc.i.IStart;
import org.wxd.boot.timer.ann.Scheduled;
import org.wxd.mmo.loginsr.data.DataCenter;


/**
 * 全服数据模型
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:24
 **/
@Slf4j
@Resource
public class ServerDataModule implements IStart, IShutdownBefore, IShutdownEnd {

    @Resource DataCenter dataCenter;

    @Override public void start(IocInjector iocInjector) throws Exception {
    }

    @Scheduled("*/5")
    public void save() throws Exception {
        dataCenter.getLoginDataHelper().getBatchPool().replace(dataCenter.getServerData());
    }

    @Override public void shutdownBefore() throws Exception {
        log.info("准备关服，开始保存全局数据");
        dataCenter.getLoginDataHelper().replace(dataCenter.getServerData());
    }

    @Override public void shutdownEnd() throws Exception {
        log.info("停服完成，开始关闭数据库");
        dataCenter.getLoginDataHelper().close();
    }


}
