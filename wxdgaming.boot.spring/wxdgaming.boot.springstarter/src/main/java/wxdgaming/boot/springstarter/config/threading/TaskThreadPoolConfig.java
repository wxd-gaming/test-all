package wxdgaming.boot.springstarter.config.threading;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 线程池配置
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-24 11:45
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "task.pool")
public class TaskThreadPoolConfig implements Serializable {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;

}
