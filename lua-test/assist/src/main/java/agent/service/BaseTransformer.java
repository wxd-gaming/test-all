package agent.service;

import java.lang.instrument.Instrumentation;

/**
 * 转化器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-09-06 10:03
 **/
public abstract class BaseTransformer implements java.lang.instrument.ClassFileTransformer {
    private final String agentArgs;
    private final Instrumentation inst;

    public BaseTransformer(String agentArgs, Instrumentation inst) {
        this.agentArgs = agentArgs;
        this.inst = inst;
    }

    public String getAgentArgs() {
        return agentArgs;
    }

    public Instrumentation getInst() {
        return inst;
    }
}
