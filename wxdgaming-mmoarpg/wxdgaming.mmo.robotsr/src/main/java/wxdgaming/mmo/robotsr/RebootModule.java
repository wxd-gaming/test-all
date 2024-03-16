package wxdgaming.mmo.robotsr;

import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.starter.UserModule;
import wxdgaming.mmo.core.bean.config.ServerConfig;

public class RebootModule extends UserModule {

    public RebootModule(ReflectContext reflectContext) {
        super(reflectContext);
    }

    @Override protected RebootModule bind() throws Exception {
        bindSingleton(ServerConfig.class, ServerConfig.getInstance());
        return this;
    }

}
