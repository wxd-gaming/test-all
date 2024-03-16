package wxdgaming.mmo.loginsr;

import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.starter.UserModule;
import wxdgaming.mmo.core.bean.config.ServerConfig;

public class LoginUserModule extends UserModule {

    public LoginUserModule(ReflectContext reflectContext) {
        super(reflectContext);
    }

    @Override public LoginUserModule bind() throws Exception {
        bindSingleton(ServerConfig.class, ServerConfig.getInstance());
        return this;
    }

}
