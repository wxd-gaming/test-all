package wxdgaming.boot.spring.starter.config.thread;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import wxdgaming.boot.core.threading.Executors;

import java.io.Serializable;

/**
 * 线程池配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-24 11:45
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "task.pool")
public class TaskThreadPoolConfig implements Serializable {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

    public TaskThreadPoolConfig() {

    }

}
