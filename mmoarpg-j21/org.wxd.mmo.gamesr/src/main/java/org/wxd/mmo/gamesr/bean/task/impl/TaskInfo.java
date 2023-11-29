package org.wxd.mmo.gamesr.bean.task.impl;


import org.wxd.boot.lang.save.ObjectSave;
import org.wxd.boot.lang.task.Condition;

import java.util.ArrayList;

/**
 * 任务
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-07-31 17:13
 **/
public class TaskInfo extends ObjectSave {

    private int modelId;
    private long acceptTime;
    /** 是否领取奖励 */
    private boolean rewards = false;
    /** 完成条件 */
    private ArrayList<Condition> conditions = new ArrayList<>();

    public boolean change(int k1, int k2, int k3, long progress) {
        boolean changed = false;
        for (Condition condition : conditions) {
            if (condition.change(k1, k2, k3, progress)) {
                changed = true;
            }
        }
        return changed;
    }


    public boolean finish() {
        for (Condition condition : conditions) {
            if (!condition.finish()) {
                return false;
            }
        }
        return true;
    }

}
