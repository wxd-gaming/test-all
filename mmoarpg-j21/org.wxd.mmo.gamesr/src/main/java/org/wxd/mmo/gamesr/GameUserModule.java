package org.wxd.mmo.gamesr;

import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.starter.UserModule;
import org.wxd.mmo.core.bean.config.ServerConfig;

public class GameUserModule extends UserModule {

    public GameUserModule(ReflectContext reflectContext) {
        super(reflectContext);
    }

    @Override public GameUserModule bind() throws Exception {
        bindSingleton(ServerConfig.class, ServerConfig.getInstance());
        return this;
    }

}
