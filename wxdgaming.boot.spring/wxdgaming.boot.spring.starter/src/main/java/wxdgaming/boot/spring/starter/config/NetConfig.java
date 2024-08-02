package wxdgaming.boot.spring.starter.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import wxdgaming.boot.spring.starter.config.bean.TcpConfig;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-01 20:04
 **/
@Slf4j
@Data
@Component
@DependsOn(value = {"threadPoolConfig"})
@ConfigurationProperties(prefix = "net-config")
public class NetConfig {

    private TcpConfig tcpSocket;
    private TcpConfig tcpSocket1;
    private TcpConfig webSocket;

    public NetConfig() {
        System.out.println("\n" + this.getClass());
    }

}
