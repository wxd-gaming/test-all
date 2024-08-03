package wxdgaming.boot.spring.starter.config.thread;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import wxdgaming.boot.core.system.JvmUtil;

import java.io.Serializable;

/**
 * 线程池配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-11-24 11:45
 */
@Slf4j
@Data
@Component
@DependsOn({"springUtil"})
@ConfigurationProperties(prefix = "executor")
public class ThreadPoolConfig implements Serializable {

    private ThreadConfig vtExecutor = new ThreadConfig(100, 300);
    private ThreadConfig defaultExecutor = new ThreadConfig(2, 4);
    private ThreadConfig logicExecutor = new ThreadConfig(6, 20);

    public ThreadPoolConfig() {
        System.out.println("\n" + this.getClass());
    }

    @Override public String toString() {
        return String.format(
                "\n{\n%20s = %s\n%20s = %s\n%20s = %s\n}",
                "vtExecutor", vtExecutor,
                "defaultExecutor", defaultExecutor,
                "logicExecutor", logicExecutor
        );
    }

    /** 初始化 */
    @PostConstruct
    public void init() {
        log.info("{}", toString());
        JvmUtil.setProperty(JvmUtil.VT_Executor_Core_Size, vtExecutor.coreSize);
        JvmUtil.setProperty(JvmUtil.VT_Executor_Max_Size, vtExecutor.maxSize);

        JvmUtil.setProperty(JvmUtil.Default_Executor_Core_Size, defaultExecutor.coreSize);
        JvmUtil.setProperty(JvmUtil.Default_Executor_Max_Size, defaultExecutor.maxSize);

        JvmUtil.setProperty(JvmUtil.Logic_Executor_Core_Size, logicExecutor.coreSize);
        JvmUtil.setProperty(JvmUtil.Logic_Executor_Max_Size, logicExecutor.maxSize);
    }

    public static class ThreadConfig {

        private int coreSize;
        private int maxSize;

        public ThreadConfig() {
        }

        public ThreadConfig(int coreSize, int maxSize) {
            this.coreSize = coreSize;
            this.maxSize = maxSize;
        }

        @Override public String toString() {
            return String.format("{coreSize=%3s, maxSize=%3s}", coreSize, maxSize);
        }
    }

}
