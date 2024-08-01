package wxdgaming.mmo.script.gamesr.gm.script;

import com.alibaba.fastjson2.JSONArray;
import com.google.inject.Singleton;
import wxdgaming.mmo.core.login.bean.user.Account;
import wxdgaming.mmo.script.gamesr.gm.Gm;
import wxdgaming.mmo.script.gamesr.gm.IGm;


/**
 * 道具类的gm命令
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-09 18:10
 **/
@Singleton
public class GoodsGmController implements IGm {

    @Gm(group = "道具", sort = 1, comment = "id 数量")
    public void add(Account account, JSONArray args) {

    }

}
