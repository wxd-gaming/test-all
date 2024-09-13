package monitor;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.HashSet;

/**
 * 配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-11 14:06
 **/
@Getter
@Setter
public class MonitorConfig {

    public Instrumentation inst;

    /** 单位毫秒 */
    private long printLimitTime = 30_000;

    private long printLimitCount = 100;
    /** 毫秒 */
    private long needTotalTime = 10;
    /** 毫秒 */
    private long needTotalCount = 10;
    /** 毫秒 */
    private long needAvgTime = 10;
    private boolean printAfterReset = false;
    private String outPath = "target";
    private boolean outInitLog = false;
    private boolean outLogConsole = false;
    private boolean outLogFile = false;
    private boolean outClass = false;
    private HashSet<String> agentArgs = new HashSet<>();
    private HashSet<String> ignoreArgs = new HashSet<>();

    public static MonitorConfig create(Instrumentation inst, String cfgPath) {
        if (cfgPath == null || cfgPath.trim().isEmpty()) {
            cfgPath = "monitor.yml";
        }
        MonitorConfig config = YamlUtil.loadYaml(cfgPath, MonitorConfig.class);
        config.inst = inst;
        if (config.getOutPath() == null || config.getOutPath().trim().isEmpty()) {
            config.outPath = "target";
        }
        config.del(new File(config.getOutPath()));
        if (config.printLimitTime > 0) {
            config.printLimitTime = Math.max(1_000L, config.printLimitTime);
        }
        config.needTotalTime = config.needTotalTime * 10000_00;
        config.needAvgTime = config.needAvgTime * 10000_00;
        return config;
    }


    void del(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    del(file1);
                }
            }
        }
        file.delete();
    }


    @Override public String toString() {
        return "printLimitTime=" + printLimitTime + "\n"
                + "printLimitCount=" + printLimitCount + "\n"
                + "needTotalTime=" + needTotalTime + "\n"
                + "needTotalCount=" + needTotalCount + "\n"
                + "needAvgTime=" + needAvgTime + "\n"
                + "printAfterReset=" + printAfterReset + "\n"
                + "outPath=" + outPath + "\n"
                + "outInitLog=" + outInitLog + "\n"
                + "outLogConsole=" + outLogConsole + "\n"
                + "outLogFile=" + outLogFile + "\n"
                + "outClass=" + outClass + "\n"
                + "agentArgs=" + agentArgs + "\n"
                + "ignoreArgs=" + ignoreArgs + "\n"
                ;
    }
}
