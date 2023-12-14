package org.wxd.mmo.robotsr;

import org.wxd.agent.system.ReflectContext;
import org.wxd.boot.starter.UserModule;
import org.wxd.mmo.bean.config.ServerConfig;

public class RebootModule extends UserModule {

    public RebootModule(ReflectContext reflectContext) {
        super(reflectContext);
    }

    @Override protected RebootModule bind() throws Exception {
        bindSingleton(ServerConfig.class, ServerConfig.getInstance());
        return this;
    }

}
