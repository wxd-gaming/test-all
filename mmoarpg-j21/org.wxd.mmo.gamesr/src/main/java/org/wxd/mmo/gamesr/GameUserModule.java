package org.wxd.mmo.gamesr;

import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.starter.UserModule;
import org.wxd.mmo.core.bean.config.ServerConfig;

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
