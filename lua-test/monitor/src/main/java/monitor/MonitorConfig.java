package monitor;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.File;
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
    private final AtomicBoolean printAfterReset = new AtomicBoolean(false);
    private final AtomicReference<String> outPath = new AtomicReference<>("target");
    private final AtomicBoolean outInitLog = new AtomicBoolean(false);
    private final AtomicBoolean outLogConsole = new AtomicBoolean(false);
    private final AtomicBoolean outLogFile = new AtomicBoolean(false);
    private final AtomicBoolean outClass = new AtomicBoolean(false);
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

            printLimitCount.set(Long.parseLong(properties.getProperty("printLimitCount", "100")));
            needTotalCount.set(Long.parseLong(properties.getProperty("needTotalCount", "10")));
            printLimitTime.set(Long.parseLong(properties.getProperty("printLimitTime", "30000")));
            needTotalTime.set(Long.parseLong(properties.getProperty("needTotalTime", "10")));
            needAvgTime.set(Long.parseLong(properties.getProperty("needAvgTime", "10")));
            printAfterReset.set(Boolean.parseBoolean(properties.getProperty("printAfterReset", "false")));
            outPath.set(properties.getProperty("outPath", "target"));
            if (outPath.get() == null || outPath.get().trim().isEmpty()) {
                outPath.set("target");
            }
            outInitLog.set(Boolean.parseBoolean(properties.getProperty("outInitLog", "false")));
            outLogConsole.set(Boolean.parseBoolean(properties.getProperty("outLogConsole", "false")));
            outLogFile.set(Boolean.parseBoolean(properties.getProperty("outLogFile", "false")));
            outClass.set(Boolean.parseBoolean(properties.getProperty("outClass", "false")));
            del(new File(outPath.get()));

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

            /*配置单位是毫秒，转化成纳秒*/
            needTotalTime.set(needTotalTime.get() * 100 * 10000);
            needAvgTime.set(needAvgTime.get() * 100 * 10000);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e);
        }
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

    BufferedReader readBody(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        InputStream inputStream = null;
        if (Files.exists(path)) {
            inputStream = Files.newInputStream(path);
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
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
                ", outPath=" + outPath +
                ", outInitLog=" + outInitLog +
                ", outLogConsole=" + outLogConsole +
                ", outLogFile=" + outLogFile +
                ", outClass=" + outClass +
                ", agentArgs=" + agentArgs +
                ", ignoreArgs=" + ignoreArgs +
                '}';
    }
}
