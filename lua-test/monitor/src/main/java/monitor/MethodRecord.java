package monitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodRecord implements Cloneable {
    private String methodName;
    private long totalCostTime;
    private long execCount;
    private long avgCost;
    private long minCost;
    private long maxCost;

    public MethodRecord(String methodName) {
        this.methodName = methodName;
    }

    public void add(long cost) {
        totalCostTime += cost;
        execCount++;
        if (minCost == 0 || minCost > cost) {
            minCost = cost;
        }
        if (maxCost < cost) {
            maxCost = cost;
        }
    }

    @Override public MethodRecord clone() {
        try {
            return (MethodRecord) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public String toString() {
        return "MethodRecord{" +
                "methodName='" + methodName + '\'' +
                ", totalCostTime=" + totalCostTime +
                ", execCount=" + execCount +
                ", minCost=" + minCost +
                ", maxCost=" + maxCost +
                '}';
    }
}
