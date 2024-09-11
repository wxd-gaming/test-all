package monitor;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 配置
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-11 14:06
 **/
@Getter
public class MonitorConfig {

    public final Instrumentation inst;

    /** 单位毫秒 */
    private final AtomicLong printLimitTime = new AtomicLong(30_000);

    private final AtomicLong printLimitCount = new AtomicLong(100);
    /** 毫秒 */
    private final AtomicLong needTotalTime = new AtomicLong(10);
    /** 毫秒 */
    private final AtomicLong needTotalCount = new AtomicLong(10);
    /** 毫秒 */
    private final AtomicLong needAvgTime = new AtomicLong(10);
    private final AtomicBoolean printAfterReset = new AtomicBoolean(true);
    private final AtomicBoolean printConsole = new AtomicBoolean(true);
    private final AtomicReference<String> printFilePath = new AtomicReference<>();
    private final AtomicReference<String> outClassPath = new AtomicReference<>();

    private final HashSet<String> agentArgs = new HashSet<>();
    private final HashSet<String> ignoreArgs = new HashSet<>();


    public MonitorConfig(Instrumentation inst, String cfgPath) {
        this.inst = inst;
        try {
            if (cfgPath == null || cfgPath.trim().isEmpty()) {
                cfgPath = "monitor-config.cfg";
            }
            BufferedReader configReader = readBody(cfgPath);
            if (configReader == null) return;
            Properties properties = new Properties();
            try {
                properties.load(configReader);
            } finally {configReader.close();}

            printLimitTime.set(Long.parseLong(properties.getProperty("printLimitTime", "30000")));
            printLimitCount.set(Long.parseLong(properties.getProperty("printLimitCount", "100")));
            needTotalCount.set(Long.parseLong(properties.getProperty("needTotalCount", "10")));
            needTotalTime.set(Long.parseLong(properties.getProperty("needTotalTime", "10")));
            needAvgTime.set(Long.parseLong(properties.getProperty("needAvgTime", "10")));
            printAfterReset.set(Boolean.parseBoolean(properties.getProperty("printAfterReset", "false")));
            printConsole.set(Boolean.parseBoolean(properties.getProperty("printConsole", "true")));
            printFilePath.set(properties.getProperty("printFilePath"));
            outClassPath.set(properties.getProperty("outClassPath"));

            BufferedReader monitorConfigAgent = readBody("monitor-config-agent.cfg");
            if (monitorConfigAgent != null) {
                try {
                    String line = null;
                    while ((line = monitorConfigAgent.readLine()) != null) {
                        String trim = line.trim();
                        if (trim.startsWith("#")) continue;
                        agentArgs.add(trim);
                    }
                    agentArgs.removeIf(e -> e == null || e.trim().isEmpty());
                } finally {
                    monitorConfigAgent.close();
                }
            }

            BufferedReader monitorConfigIgnore = readBody("monitor-config-ignore.cfg");
            if (monitorConfigIgnore != null) {
                try {
                    String line = null;
                    while ((line = monitorConfigIgnore.readLine()) != null) {
                        String trim = line.trim();
                        if (trim.startsWith("#")) continue;
                        ignoreArgs.add(trim);
                    }
                    ignoreArgs.removeIf(e -> e == null || e.trim().isEmpty());
                } finally {
                    monitorConfigIgnore.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
    }

    BufferedReader readBody(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        InputStream inputStream = null;
        if (Files.exists(path)) {
            inputStream = Files.newInputStream(path);
        } else {
            inputStream = MonitorConfig.class.getResourceAsStream("/" + fileName);
        }
        if (inputStream == null) return null;
        InputStreamReader in = new InputStreamReader(inputStream);
        return new BufferedReader(in);
    }

    @Override public String toString() {
        return "MonitorConfig{" +
                "printLimitTime=" + printLimitTime +
                ", printLimitCount=" + printLimitCount +
                ", needTotalTime=" + needTotalTime +
                ", needTotalCount=" + needTotalCount +
                ", needAvgTime=" + needAvgTime +
                ", printAfterReset=" + printAfterReset +
                ", outPath=" + outClassPath.get() +
                ", agentArgs=" + agentArgs +
                ", ignoreArgs=" + ignoreArgs +
                '}';
    }
}
