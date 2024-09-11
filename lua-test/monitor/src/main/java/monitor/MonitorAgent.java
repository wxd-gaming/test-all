package monitor;

import java.lang.instrument.Instrumentation;

/**
 * 代理监控类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-10 20:44
 **/
public class MonitorAgent {

    public static MonitorConfig monitorConfig;

    public static void premain(String agentArgs, Instrumentation inst) {
        monitorConfig = new MonitorConfig(inst, agentArgs);
        System.out.println("monitor agent init " + monitorConfig.toString());
        MonitorRecord.init();
        inst.addTransformer(new MonitorTransformer());
    }


}
