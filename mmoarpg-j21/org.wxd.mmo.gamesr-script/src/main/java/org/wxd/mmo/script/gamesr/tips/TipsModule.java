package org.wxd.mmo.script.gamesr.tips;

import org.wxd.boot.ioc.ann.Resource;
import org.wxd.mmo.gamesr.bean.user.Player;
import org.wxd.mmo.script.gamesr.tips.message.ResTips;


/**
 * 提示通用类
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-10 15:20
 **/
@Resource
public class TipsModule {

    public void sendTips(Player player, String content) {
        ResTips build = ResTips.newBuilder().setType(2).setLanCode(content).build();

    }

}