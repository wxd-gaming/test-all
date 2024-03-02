package org.wxd.mmo.core.common.cache.cfg;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.batis.store.JsonDataRepository;
import org.wxd.boot.batis.struct.DbBean;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.cfg.Cfg;


/**
 * 策划配置缓存
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 14:50
 **/
@Slf4j
@Singleton
public class JsonConfigCache extends JsonDataRepository implements IBeanInit {

    public JsonConfigCache() {
        setPath("cfg-json");
    }

    /**
     * bean初始化调用的，即便是热更新也会调用，会优先处理ioc注入
     *
     * @param iocContext
     */
    @Override public void beanInit(IocContext iocContext) throws Exception {
        ReflectContext build = ReflectContext.Builder.of(JsonConfigCache.class.getClassLoader(), Cfg.class.getPackageName()).build();
        build.withSuper(DbBean.class).forEach(factroy -> {
            DbBean dbBean = getDbBean(factroy.getCls());
            log.info("读取配置：{}(module={}) - size={}", dbBean.getDataStruct().getTableComment(), dbBean.getEntityClass().getSimpleName(), dbBean.dbSize());
        });
    }
}
