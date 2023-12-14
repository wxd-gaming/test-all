package org.wxd.mmo.script.gamesr.gm.script;

import com.alibaba.fastjson2.JSONArray;
import com.google.inject.Singleton;
import org.wxd.mmo.login.bean.user.Account;
import org.wxd.mmo.script.gamesr.gm.Gm;
import org.wxd.mmo.script.gamesr.gm.IGm;


/**
 * 道具类的gm命令
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-09 18:10
 **/
@Singleton
public class GoodsGmController implements IGm {

    @Gm(group = "道具", sort = 1, comment = "id 数量")
    public void add(Account account, JSONArray args) {

    }

}
