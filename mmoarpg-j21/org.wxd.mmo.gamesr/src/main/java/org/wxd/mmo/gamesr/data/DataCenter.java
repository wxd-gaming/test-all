package org.wxd.mmo.gamesr.data;

import lombok.Getter;
import lombok.Setter;
import org.wxd.boot.batis.mongodb.MongoDataHelper;
import org.wxd.boot.ioc.IocInjector;
import org.wxd.boot.ioc.ann.Resource;
import org.wxd.boot.ioc.i.IBeanInit;
import org.wxd.boot.net.web.ws.WebSession;
import org.wxd.boot.net.web.ws.WebSocketClient;
import org.wxd.mmo.bean.config.ServerConfig;
import org.wxd.mmo.bean.data.UidSeed;
import org.wxd.mmo.gamesr.bean.data.ServerData;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 数据中心
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:20
 **/
@Getter
@Setter
@Resource
public class DataCenter implements IBeanInit {

    private static DataCenter instance = null;

    public static DataCenter getInstance() {
        return instance;
    }

    @Resource MongoDataHelper mongoDataHelper;

    /** 全局的临时id，比如掉落物，怪物的唯一id */
    private final AtomicLong uidSeed = new AtomicLong();

    private WebSocketClient<WebSession> loginSocket = null;

    /** 全局数据 */
    private TreeMap<Integer, ServerData> serverDataMap = new TreeMap<>();

    public DataCenter() {

    }

    @Override public void beanInit(IocInjector iocInjector) throws Exception {
        DataCenter.instance = this;
    }

    public ServerData serverData() {
        return serverData(ServerConfig.getInstance().getSid());
    }

    public ServerData serverData(int sid) {
        ServerData serverData = serverDataMap.computeIfAbsent(sid, s -> new ServerData().setSid(sid));
        return serverData;
    }

    public long newUid(UidSeed.Type type) {
        return newUid(type, ServerConfig.getInstance().getSid());
    }

    public long newUid(UidSeed.Type type, int hexId) {
        return serverData().getUidSeed().newUid(type, hexId);
    }

    /** 场景对象的临时uid, 比如掉落物，怪物的唯一id */
    public long newUid() {
        return uidSeed.incrementAndGet();
    }

}
