package wxdgaming.mmo.core.bean.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.core.lang.ObjectBase;
import wxdgaming.boot.core.timer.MyClock;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务器标记配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-08-02 20:45
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ServerConfig extends ObjectBase {

    private static final ServerConfig instance;

    public static final ServerConfig getInstance() {
        return instance;
    }

    static {
        instance = new ServerConfig();
        instance.sid = (6001);
        instance.openTime = MyClock.millis();
    }

    private int sid;
    private String sName = "";
    private long openTime;
    private int groupId;
    private String groupName = "";
    private List<Integer> subIds = new ArrayList<>();

    public boolean checkSid(int sid) {
        return this.sid == sid || this.subIds.contains(sid);
    }

}
