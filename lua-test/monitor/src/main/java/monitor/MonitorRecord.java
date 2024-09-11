package monitor;

import java.lang.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * 获得监控
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-11 09:03
 **/
public class MonitorRecord {

    static ConcurrentSkipListMap<String, MethodRecord> classes = new ConcurrentSkipListMap<>();

    public static void add(String methodName, long start) {
        classes.computeIfAbsent(methodName, MethodRecord::new)
                .add((System.nanoTime() - start) / 10000);
    }

    public static void init() {
        if (MonitorAgent.monitorConfig.getPrintLimitTime().get() > 0) {
            MonitorAgent.monitorConfig.getPrintLimitTime().set(Math.max(10_000L, MonitorAgent.monitorConfig.getPrintLimitTime().get()));
            Thread monitorThread = new Thread(() -> {
                while (true) {
                    System.out.println(record2String());
                    try {
                        Thread.sleep(MonitorAgent.monitorConfig.getPrintLimitTime().get());
                    } catch (InterruptedException ignore) {
                        break;
                    }
                }
            });
            monitorThread.setDaemon(true);
            monitorThread.start();
        }
    }

    public static Collection<MethodRecord> reset() {
        Collection<MethodRecord> values = classes.values();
        classes = new ConcurrentSkipListMap<>();
        return values;
    }

    public static String record2String() {
        Collection<MethodRecord> values;
        if (MonitorAgent.monitorConfig.getPrintAfterReset().get()) {
            values = reset();
        } else {
            values = classes.values();
        }

        List<MethodRecord> collect = values.stream()
                .map(mr -> {
                    MethodRecord clone = mr.clone();
                    clone.setAvgCost(mr.getTotalCostTime() / mr.getExecCount());
                    return clone;
                })
                .filter(mr -> mr.getAvgCost() > 0.01f)
                .filter(mr -> mr.getTotalCostTime() > MonitorAgent.monitorConfig.getNeedTotalTime().get() * 100)
                .filter(mr -> mr.getExecCount() > MonitorAgent.monitorConfig.getNeedTotalCount().get())
                .filter(mr -> mr.getAvgCost() > MonitorAgent.monitorConfig.getNeedAvgTime().get() * 100)
                .sorted((o1, o2) -> {
                    if (o2.getAvgCost() != o1.getAvgCost())
                        return Long.compare(o2.getAvgCost(), o1.getAvgCost());
                    if (o2.getTotalCostTime() != o1.getTotalCostTime())
                        return Long.compare(o2.getTotalCostTime(), o1.getTotalCostTime());
                    if (o2.getExecCount() != o1.getExecCount())
                        return Long.compare(o2.getExecCount(), o1.getExecCount());
                    return o2.getMethodName().compareTo(o1.getMethodName());
                })
                .limit(MonitorAgent.monitorConfig.getPrintLimitCount().get())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        String format = "%-18s\t%-20s\t%-12s\t%-12s\t%-12s\t%s";
        sb.append(
                String.format(
                        format,
                        "execCount", "totalTime(ms)", "avgCost(ms)", "minCost(ms)", "maxCost(ms)", "methodName")
        ).append("\n");

        for (MethodRecord methodRecord : collect) {
            sb.append(String.format(
                    format,
                    methodRecord.getExecCount(),
                    methodRecord.getTotalCostTime() / 100f,
                    methodRecord.getAvgCost() / 100f,
                    methodRecord.getMinCost() / 100f,
                    methodRecord.getMaxCost() / 100f,
                    methodRecord.getMethodName()
            )).append("\n");
        }

        return sb.toString();
    }
}
