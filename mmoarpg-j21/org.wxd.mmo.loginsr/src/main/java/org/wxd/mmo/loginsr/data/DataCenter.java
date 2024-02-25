package org.wxd.mmo.loginsr.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.Starter;
import org.wxd.boot.starter.batis.MysqlService;
import org.wxd.boot.starter.batis.MysqlService1;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.loginsr.bean.data.ServerData;


/**
 * 数据中心
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-04 20:14
 **/
@Slf4j
@Getter
@Accessors(chain = true)
@Singleton
public class DataCenter implements IBeanInit {

    @Getter private static DataCenter instance = null;

    @Inject private MysqlService1 loginDb;

    @Setter private ServerData serverData;

    @Override public void beanInit(IocContext iocContext) throws Exception {
        instance = this;
        loginDb.checkDataBase("org.wxd.mmo.loginsr");
    }

}
