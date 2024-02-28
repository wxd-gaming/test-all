package org.wxd.mmo.core.struct;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.boot.core.lang.ObjectBase;
import org.wxd.mmo.core.bean.type.Platforms;

/**
 * 服务器信息
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-27 21:11
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ServerInfo extends ObjectBase {

    private Platforms platforms;
    private int serverId;
    private String serverName;

}
