package org.wxd.mmo.gamesr.data;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import org.wxd.boot.core.lang.rank.RankMap;
import org.wxd.boot.core.lang.rank.RankScore;
import org.wxd.boot.net.web.ws.WebSession;
import org.wxd.boot.net.web.ws.WebSocketClient;
import org.wxd.boot.starter.IocContext;
import org.wxd.boot.starter.batis.MysqlService;
import org.wxd.boot.starter.batis.MysqlService1;
import org.wxd.boot.starter.i.IBeanInit;
import org.wxd.mmo.core.bean.config.ServerConfig;
import org.wxd.mmo.core.bean.data.UidSeed;
import org.wxd.mmo.gamesr.bean.data.ServerData;
import org.wxd.mmo.core.bean.rank.RankType;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 数据中心
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 20:20
 **/
@Getter
@Setter
@Singleton
public class DataCenter implements IBeanInit {

    @Getter private static DataCenter instance = null;

    @Inject MysqlService gameDb;
    @Inject MysqlService1 loginDb;

    /** 全局的临时id，比如掉落物，怪物的唯一id */
    private final AtomicLong uidSeed = new AtomicLong();

    private WebSocketClient<WebSession> loginSocket = null;

    /** 全局数据 */
    private TreeMap<Integer, ServerData> serverDataMap = new TreeMap<>();

    private ConcurrentHashMap<RankType, RankMap<Long, RankScore<Long>>> ranks = new ConcurrentHashMap<>();

    @Override public void beanInit(IocContext iocContext) throws Exception {
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
