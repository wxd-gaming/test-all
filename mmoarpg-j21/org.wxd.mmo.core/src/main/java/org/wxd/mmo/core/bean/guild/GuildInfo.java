package org.wxd.mmo.core.bean.guild;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.wxd.mmo.core.GameBase;
import org.wxd.mmo.core.bean.bag.ItemPack;
import org.wxd.mmo.core.bean.bag.PackType;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 公会信息
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-22 14:15
 **/
@Getter
@Setter
@Accessors(chain = true)
public class GuildInfo extends GameBase {

    /** 名字 */
    private String guildName;
    /** 公告 */
    private String notice;
    /** 成员 */
    private ConcurrentHashMap<Long, GuildMember> memberMap = new ConcurrentHashMap<>();
    /** 公会仓库 */
    private ItemPack itemPack = new ItemPack(PackType.GUILD, 100);

}
