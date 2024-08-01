package wxdgaming.boot.spring.starter.config.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.boot.core.lang.ObjectBase;
import wxdgaming.boot.core.str.StringUtil;
import wxdgaming.boot.net.http.ssl.SslContextServer;
import wxdgaming.boot.net.http.ssl.SslProtocolType;

import javax.net.ssl.SSLContext;
import java.io.Serializable;

/**
 * tcp网络配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2021-09-30 09:33
 **/
@Getter
@Setter
@Accessors(chain = true)
public class TcpConfig extends ObjectBase implements Serializable {

    @JSONField(ordinal = 1)
    private String name = "";
    /** 外网ip */
    @JSONField(ordinal = 2)
    private String wanIp = "0.0.0.0";
    /** 内网ip */
    @JSONField(ordinal = 3)
    private String host = "";
    /** 监听端口 */
    @JSONField(ordinal = 4)
    private int port = 0;
    @JSONField(ordinal = 5)
    private String sslProtocolType = null;
    @JSONField(ordinal = 6)
    private String jks = "";
    @JSONField(ordinal = 7)
    private String jksPwd = "";

    public SslProtocolType sslProtocolType() throws Exception {
        if (StringUtil.notEmptyOrNull(sslProtocolType)) {
            return SslProtocolType.of(sslProtocolType);
        }
        return SslProtocolType.TLSV12;
    }

    public SSLContext sslContext() throws Exception {
        return SslContextServer.sslContext(sslProtocolType(), jks, jksPwd);
    }

}
