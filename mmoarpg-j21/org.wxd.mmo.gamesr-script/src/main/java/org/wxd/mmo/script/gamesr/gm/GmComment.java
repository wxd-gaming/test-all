package org.wxd.mmo.script.gamesr.gm;

import org.wxd.boot.collection.ObjMap;
import org.wxd.boot.net.controller.ann.TextController;
import org.wxd.boot.net.controller.ann.TextMapping;
import org.wxd.boot.net.controller.cmd.RunCode;
import org.wxd.boot.net.web.hs.HttpSession;
import org.wxd.boot.net.web.hs.controller.cmd.HttpFtp;
import org.wxd.boot.str.StringUtil;
import org.wxd.boot.system.JvmUtil;
import org.wxd.mmo.gamesr.GameSrAppMain;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:38
 **/
@TextController(url = "gm")
public class GmComment implements HttpFtp, RunCode {

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