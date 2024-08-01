package wxdgaming.mmo.core.cfg;

import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.batis.store.JsonDataRepository;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IBeanInit;


/**
 * 策划配置缓存
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-03 14:50
 **/
@Slf4j
@Singleton
public class CfgCache extends JsonDataRepository implements IBeanInit {

    public CfgCache() {
        setPath("cfg-json");
    }

    public CfgCache(String jsonPath) {
        setPath(jsonPath);
    }

    /**
     * bean初始化调用的，即便是热更新也会调用，会优先处理ioc注入
     *
     * @param iocContext
     */
    @Override public void beanInit(IocContext iocContext) throws Exception {
        load(false,CfgCache.class.getClassLoader(), this.getClass().getPackageName());

        //ReflectContext context = ReflectContext.Builder.of(CfgCache.class.getClassLoader(), this.getClass().getPackageName()).build();
        //context.withSuper(DbBean.class).forEach(factroy -> {
        //    DbBean dbBean = getDbBean(factroy.getCls());
        //    log.info(
        //            "读取配置：{}(module={}) - size={}",
        //            dbBean.getDataStruct().getTableComment(),
        //            dbBean.getEntityClass().getSimpleName(),
        //            dbBean.dbSize()
        //    );
        //});
    }
}
