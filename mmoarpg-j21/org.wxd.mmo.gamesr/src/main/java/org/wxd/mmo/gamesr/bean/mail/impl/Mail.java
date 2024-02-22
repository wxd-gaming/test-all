package org.wxd.mmo.gamesr.bean.mail.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.mmo.core.GameBase;
import org.wxd.mmo.core.bean.bag.goods.Item;

import java.util.List;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-03 20:12
 **/
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Mail extends GameBase {

    private long senderId;
    private String sender;
    /** 前端判定如果是纯数字则为语言包 */
    private String title;
    /** 前端判定如果是纯数字则为语言包 */
    private String content;
    /** 需要约定比如 #123 则是语言包 #I123则为道具ID */
    private List<String> contentParams = null;
    private boolean read = false;
    private boolean reward = false;
    private List<Item> items = null;
    /** 邮件来源备注 */
    private String logs;

    public Mail(long senderId, String sender, String title, String content, List<String> contentParams, List<Item> items, String logs) {
        this.senderId = senderId;
        this.sender = sender;
        this.title = title;
        this.content = content;
        this.contentParams = contentParams;
        this.items = items;
        this.logs = logs;
    }

    @Override public Mail setUid(long uid) {
        super.setUid(uid);
        return this;
    }

}
