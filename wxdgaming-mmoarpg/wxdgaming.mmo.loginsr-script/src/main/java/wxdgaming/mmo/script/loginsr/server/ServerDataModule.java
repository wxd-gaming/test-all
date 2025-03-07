package wxdgaming.mmo.script.loginsr.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.core.ann.Sort;
import wxdgaming.boot.core.timer.ann.Scheduled;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IBeanInit;
import wxdgaming.boot.starter.i.IShutdownBefore;
import wxdgaming.boot.starter.i.IShutdownEnd;
import wxdgaming.boot.starter.i.IStart;
import wxdgaming.mmo.loginsr.bean.data.ServerData;
import wxdgaming.mmo.loginsr.data.DataCenter;


/**
 * 全服数据模型
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 16:24
 **/
@Slf4j
@Singleton
public class ServerDataModule implements IStart, IShutdownBefore, IShutdownEnd {

    @Inject DataCenter dataCenter;

    /**
     * bean初始化调用的，特别注意热更新是不调用，优先调用的是{@link IBeanInit#beanInit(IocContext)}
     */
    @Sort(0)
    @Override public void start(IocContext iocContext) throws Exception {
        dataCenter.setServerData(dataCenter.getLoginDb().queryEntity(ServerData.class, 1L));
        if (dataCenter.getServerData() == null) {
            dataCenter.setServerData(new ServerData().setUid(1));
        }
    }

    @Scheduled("*/5")
    public void save() throws Exception {
        dataCenter.getLoginDb().getBatchPool().replace(dataCenter.getServerData());
    }

    @Override public void shutdownBefore() throws Exception {
        log.info("准备关服，开始保存全局数据");
        dataCenter.getLoginDb().replace(dataCenter.getServerData());
    }

    @Override public void shutdownEnd() throws Exception {
        log.info("停服完成，开始关闭数据库");
        dataCenter.getLoginDb().close();
    }


}
