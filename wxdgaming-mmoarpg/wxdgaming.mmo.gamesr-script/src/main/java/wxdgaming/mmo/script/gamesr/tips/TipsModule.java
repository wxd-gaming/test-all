package wxdgaming.mmo.script.gamesr.tips;

import com.google.inject.Singleton;
import wxdgaming.mmo.gamesr.bean.user.Player;
import wxdgaming.mmo.script.gamesr.tips.message.ResTips;
import wxdgaming.mmo.script.gamesr.tips.message.TipsType;


/**
 * 提示通用类
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-10 15:20
 **/
@Singleton
public class TipsModule {

    public void sendTips(Player player, String content) {
        ResTips build = ResTips.newBuilder().setType(TipsType.normal).setLanCode(content).build();
        player.getSocketSession().writeFlush(build);
    }

    /***
     * 通知客户端异常了
     * @param player
     * @param content 提示内容
     * @param resId 客户端当前正在监听等待返回的消息id
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-02-29 20:48
     */
    public void sendError(Player player, String content, int resId) {
        ResTips build = ResTips.newBuilder()
                .setType(TipsType.error)
                .setLanCode(content)
                .setResId(resId)
                .build();
        player.getSocketSession().writeFlush(build);
    }

}
