package wxdgaming.mmo.gamesr;

import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.starter.UserModule;
import wxdgaming.mmo.core.bean.config.ServerConfig;

/**
 * 用户模块的绑定配置
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-29 14:45
 */
public class GameUserModule extends UserModule {

    public GameUserModule(ReflectContext reflectContext) {
        super(reflectContext);
    }

    @Override public GameUserModule bind() throws Exception {
        bindSingleton(ServerConfig.class, ServerConfig.getInstance());
        return this;
    }

}
