package org.wxd.mmo.robotsr;

import org.wxd.boot.ioc.IocConfig;
import org.wxd.mmo.bean.config.ServerConfig;

public class BindConfig extends IocConfig {

    @Override public void action() throws Exception {
        bind(ServerConfig.class, ServerConfig.getInstance());
    }

}
