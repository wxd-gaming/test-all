package wxdgaming.mmo.script.gamesr.gm;

import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import wxdgaming.boot.agent.system.AnnUtil;
import wxdgaming.boot.starter.IocContext;
import wxdgaming.boot.starter.i.IStart;
import wxdgaming.mmo.script.gamesr.gm.message.GmBean;
import wxdgaming.mmo.script.gamesr.gm.message.GmGroup;
import wxdgaming.mmo.script.gamesr.gm.message.ResGmList;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * gm模块管理
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-09 17:04
 **/
@Slf4j
@Getter
@Singleton
public class GmModule implements IStart {

    private ConcurrentSkipListMap<String, GmMappingInfo> gmMap = new ConcurrentSkipListMap<>();
    private ResGmList resGmList;

    @Override public void start(IocContext context) throws Exception {
        ResGmList.Builder builder = ResGmList.newBuilder();
        HashMap<String, GmGroup.Builder> groupMap = new HashMap<>();
        context.forEachBean(IGm.class, iGm -> {
            Method[] methods = iGm.getClass().getMethods();
            for (Method method : methods) {
                Gm gm = AnnUtil.ann(method, Gm.class);
                if (gm == null) {
                    /*忽律字段*/
                    continue;
                }
                String gmName = method.getName().toLowerCase();
                if (gmMap.containsKey(gmName)) {
                    throw new RuntimeException("存在重复的gm命令：" + gmName + " - " + iGm.getClass().getName());
                }

                gmMap.put(gmName, new GmMappingInfo().setInstance(iGm).setMethod(method).setGm(gm));
                if (log.isDebugEnabled()) {
                    log.debug("注册gm命令：{group={}, defaultValue={}, sort={}, comment={}}", gm.group(), gm.defaultValue(), gm.sort(), gm.comment());
                }
                GmBean.Builder builder1 = GmBean.newBuilder();
                builder1.setComment(gm.comment()).setName(gmName).setDefaultValue(gm.defaultValue());

                groupMap.computeIfAbsent(gm.group(), l -> GmGroup.newBuilder()).addGms(builder1);
            }
        });

        for (GmGroup.Builder value : groupMap.values()) {
            builder.addGroups(value);
        }
        resGmList = builder.build();
    }

}
