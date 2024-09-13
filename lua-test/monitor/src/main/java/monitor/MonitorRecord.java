package monitor;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Comparator;
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

    static ConcurrentSkipListMap<String, MethodRecord> totalMonitorMap = new ConcurrentSkipListMap<>();

    public static void add(String methodName, long start) {
        totalMonitorMap.computeIfAbsent(methodName, MethodRecord::new)
                .add((System.nanoTime() - start));
    }

    public static void init() {
        if (MonitorAgent.monitorConfig.getPrintLimitTime() > 0) {
            Thread monitorThread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(MonitorAgent.monitorConfig.getPrintLimitTime());
                        write();
                    } catch (InterruptedException ignore) {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                    }
                }
            });
            monitorThread.setDaemon(true);
            monitorThread.start();
        }
    }

    public static void write() {
        long limit = MonitorAgent.monitorConfig.getPrintLimitCount();
        String content = record2String(limit, (o1, o2) -> {
            if (o2.getTotal().getAvgCost() != o1.getTotal().getAvgCost())
                return Long.compare(o2.getTotal().getAvgCost(), o1.getTotal().getAvgCost());
            if (o2.getTotal().getTotalExecTime() != o1.getTotal().getTotalExecTime())
                return Long.compare(o2.getTotal().getTotalExecTime(), o1.getTotal().getTotalExecTime());
            if (o2.getTotal().getTotalExecCount() != o1.getTotal().getTotalExecCount())
                return Long.compare(o2.getTotal().getTotalExecCount(), o1.getTotal().getTotalExecCount());
            return o2.getMethodName().compareTo(o1.getMethodName());
        });

        if (MonitorAgent.monitorConfig.isOutLogConsole()) {
            System.out.println(content);
        }

        if (!MonitorAgent.monitorConfig.isOutLogFile()) return;
        write("monitor.log", content);
        write("monitor-exec-count.log", record2String(Math.max(800, limit), (o1, o2) -> {
            if (o2.getTotal().getTotalExecCount() != o1.getTotal().getTotalExecCount())
                return Long.compare(o2.getTotal().getTotalExecCount(), o1.getTotal().getTotalExecCount());
            return o2.getMethodName().compareTo(o1.getMethodName());
        }));
        write("monitor-total-time.log", record2String(Math.max(800, limit), (o1, o2) -> {
            if (o2.getTotal().getTotalExecTime() != o1.getTotal().getTotalExecTime())
                return Long.compare(o2.getTotal().getTotalExecTime(), o1.getTotal().getTotalExecTime());
            return o2.getMethodName().compareTo(o1.getMethodName());
        }));
        write("monitor-min-time.log", record2String(Math.max(800, limit), (o1, o2) -> {
            if (o2.getTotal().getMinCost() != o1.getTotal().getMinCost())
                return Long.compare(o2.getTotal().getMinCost(), o1.getTotal().getMinCost());
            return o2.getMethodName().compareTo(o1.getMethodName());
        }));
    }

    /** 覆盖 */
    public static void write(String fileName, String content) {
        String first = MonitorAgent.monitorConfig.getOutPath() + "/" + fileName;
        try {
            Path path = Paths.get(first);
            File parentFile = path.toFile().getParentFile();
            if (parentFile != null)
                parentFile.mkdirs();

            Files.write(
                    path,
                    content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (Exception e) {
            throw new RuntimeException(first, e);
        }
    }

    public static Collection<MethodRecord> reset() {
        Collection<MethodRecord> values = totalMonitorMap.values();
        totalMonitorMap = new ConcurrentSkipListMap<>();
        return values;
    }

    public static String record2String(long limit, Comparator<MethodRecord> comparator) {
        Collection<MethodRecord> values;
        if (MonitorAgent.monitorConfig.isPrintAfterReset()) {
            values = reset();
        } else {
            values = totalMonitorMap.values();
        }

        List<MethodRecord> collect = values.stream()
                .map(MethodRecord::clone)
                /*执行次数控制一下*/
                .filter(mr -> mr.getTotal().getTotalExecCount() >= MonitorAgent.monitorConfig.getNeedTotalCount())
                /*执行时间和平均时间满足一项即可*/
                .filter(mr -> mr.getTotal().getTotalExecTime() >= MonitorAgent.monitorConfig.getNeedTotalTime()
                        || mr.getTotal().getAvgCost() >= MonitorAgent.monitorConfig.getNeedAvgTime()
                )
                .sorted(comparator)
                .limit(limit)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append(MonitorAgent.monitorConfig.toString()).append("\n");
        sb.append("全部监控数量：").append(values.size()).append("\n");
        sb.append("符合监控的数量：").append(collect.size()).append("\n");
        String format = "%-18s\t%-20s\t%-12s\t%-12s\t%-12s\t%-12s\t%-12s\t%-12s\t%s";
        sb.append(
                String.format(
                        format,
                        "execCount", "totalTime(ns)", "avgCost(ms)", "avg5m(ms)", "avg30m(ms)", "avg60m(ms)", "minCost(ms)", "maxCost(ms)", "methodName")
        ).append("\n");

        for (MethodRecord methodRecord : collect) {
            sb.append(String.format(
                    format,
                    methodRecord.getTotal().getTotalExecCount(),
                    decimalFormat2.format(methodRecord.getTotal().getTotalExecTime()),
                    decimalFormat5.format(methodRecord.getTotal().getAvgCost() / 1000000f),
                    decimalFormat5.format(methodRecord.getS5().getAvgCost() / 1000000f),
                    decimalFormat5.format(methodRecord.getS30().getAvgCost() / 1000000f),
                    decimalFormat5.format(methodRecord.getS60().getAvgCost() / 1000000f),
                    decimalFormat5.format(methodRecord.getTotal().getMinCost() / 1000000f),
                    decimalFormat5.format(methodRecord.getTotal().getMaxCost() / 1000000f),
                    methodRecord.getMethodName()
            )).append("\n");
        }

        return sb.toString();
    }

    static final DecimalFormat decimalFormat2 = new DecimalFormat("#"); // 根据需要定制格式
    static final DecimalFormat decimalFormat5 = new DecimalFormat("#.#####"); // 根据需要定制格式
}
