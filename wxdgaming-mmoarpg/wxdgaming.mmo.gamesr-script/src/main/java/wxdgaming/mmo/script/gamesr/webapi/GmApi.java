package wxdgaming.mmo.script.gamesr.webapi;

import wxdgaming.boot.core.collection.ObjMap;
import wxdgaming.boot.core.str.StringUtil;
import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.net.controller.ann.TextController;
import wxdgaming.boot.net.controller.ann.TextMapping;
import wxdgaming.boot.net.web.hs.HttpSession;
import wxdgaming.boot.net.web.hs.controller.cmd.HttpFtp;
import wxdgaming.boot.net.web.hs.controller.cmd.HttpSignCheck;
import wxdgaming.boot.starter.net.controller.RunCode;
import wxdgaming.mmo.gamesr.GameSrAppMain;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2023-02-02 16:38
 **/
@TextController(url = "gm")
public class GmApi implements HttpSignCheck, HttpFtp, RunCode {

    @TextMapping
    public String index() throws Exception {
        return "我磨叽：" + String.valueOf(this.hashCode());
    }

    /*


     */
    @TextMapping(remarks = "热更代码")
    public String reLoadScript() throws Exception {
        GameSrAppMain.initScript();
        return "热更代码：" + String.valueOf(this.hashCode());
    }

    @TextMapping
    @Override public void ftp(HttpSession httpSession, ObjMap putData) throws Exception {
        String path = putData.getString("path");
        final String userHome = JvmUtil.userHome();
        if (StringUtil.emptyOrNull(path) || !path.startsWith(userHome)) {
            putData.put("path", userHome + "/" + "");
        }
        HttpFtp.super.ftp(httpSession, putData);
    }

}
