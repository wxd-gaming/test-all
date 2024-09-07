package agent.service;

import agent.assist.AssistTransformer;
import agent.bytebuddy.BytebuddyTransformer;

import java.lang.instrument.Instrumentation;

/**
 * 代理类
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-06 09:53
 **/
public class PremainService {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new BytebuddyTransformer(agentArgs, inst));
        inst.addTransformer(new AssistTransformer(agentArgs, inst));
    }

}
