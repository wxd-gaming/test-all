package wxdgaming.mmo.core.bean.guild;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import wxdgaming.mmo.core.GameBase;
import wxdgaming.mmo.core.bean.bag.ItemPack;
import wxdgaming.mmo.core.bean.bag.PackType;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 公会信息
 *
 * @author: wxd-gaming(無心道, 15388152619)
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
