package monitor;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class MethodRecord implements Cloneable {

    private final String methodName;
    private final Record total = new Record();
    /** 过去5分钟 */
    private Record s5 = new Record();
    /** 过去30分钟 */
    private Record s30 = new Record();
    /** 过去60分钟 */
    private Record s60 = new Record();

    public MethodRecord(String methodName) {
        this.methodName = methodName;
    }

    public void add(long cost) {
        total.add(cost);
        check();
        s5.add(cost);
        s30.add(cost);
        s60.add(cost);
    }

    public void check() {
        if (System.currentTimeMillis() - s5.getStartTime() > TimeUnit.MINUTES.toMillis(5)) s5 = new Record();
        if (System.currentTimeMillis() - s30.getStartTime() > TimeUnit.MINUTES.toMillis(30)) s30 = new Record();
        if (System.currentTimeMillis() - s60.getStartTime() > TimeUnit.MINUTES.toMillis(60)) s60 = new Record();
    }

    public void avg() {
        check();
        total.avg();
        s5.avg();
        s30.avg();
        s60.avg();
    }

    @Override public MethodRecord clone() {
        try {
            MethodRecord clone = (MethodRecord) super.clone();
            clone.avg();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    public class Record {

        private final long startTime;
        /** 总执行时间 */
        private long totalExecTime;
        /** 总执行时间 */
        private long totalExecCount;
        /** 最小耗时 */
        private long minCost;
        /** 最大耗时 */
        private long maxCost;
        /** 平均耗时 */
        private long avgCost;

        public Record() {
            this.startTime = System.currentTimeMillis();
        }

        public void add(long cost) {
            totalExecTime += cost;
            totalExecCount++;
            if (minCost > cost || minCost == 0) {
                minCost = cost;
            }
            if (maxCost < cost) {
                maxCost = cost;
            }
        }

        public void avg() {
            if (totalExecCount > 0) {
                if (totalExecCount > 10) {
                    avgCost = (totalExecTime - minCost - maxCost) / (totalExecCount - 2);
                } else {
                    avgCost = totalExecTime / totalExecCount;
                }
            }
        }

    }

    @Override public String toString() {
        return "MethodRecord{" +
                "methodName='" + methodName + '\'' +
                ", totalCostTime=" + total.totalExecTime +
                ", execCount=" + total.totalExecCount +
                ", minCost=" + total.minCost +
                ", maxCost=" + total.maxCost +
                '}';
    }

}
