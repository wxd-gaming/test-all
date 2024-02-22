package org.wxd.mmo.loginsr;

import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.starter.UserModule;
import org.wxd.mmo.core.bean.config.ServerConfig;

public class LoginUserModule extends UserModule {

    public LoginUserModule(ReflectContext reflectContext) {
        super(reflectContext);
    }

    @Override public LoginUserModule bind() throws Exception {
        bindSingleton(ServerConfig.class, ServerConfig.getInstance());
        return this;
    }

}
