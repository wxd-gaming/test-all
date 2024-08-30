package assist;

import java.lang.instrument.Instrumentation;

/**
 * 动态探针初始化类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-29 17:15
 */
public class AssistMonitor {

    public static AssistClassTransform transformer = null;

    public static void premain(String ages, Instrumentation instrumentation) {
        transformer = new AssistClassTransform();
        instrumentation.addTransformer(transformer);
    }

    // 如果没有实现上面的方法，JVM将尝试调用该方法
    public static void premain(String agentArgs) {
    }

}
