package org.wxd.mmo.loginsr.data;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.batis.mongodb.MongoDataHelper;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IBeanInit;
import org.wxd.mmo.loginsr.bean.data.ServerData;


/**
 * 数据中心
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 20:14
 **/
@Slf4j
@Getter
@Resource
public class DataCenter implements IBeanInit {

    @Getter private static DataCenter instance = null;

    @Resource(name = "mongo-login")
    private MongoDataHelper loginDataHelper;

    private ServerData serverData;

    @Override public void beanInit(IocInjector iocInjector) throws Exception {
        instance = this;
        serverData = loginDataHelper.queryEntity(ServerData.class, 1L);
        if (serverData == null) {
            serverData = new ServerData();
            serverData.setUid(1L);
        }
    }

}
