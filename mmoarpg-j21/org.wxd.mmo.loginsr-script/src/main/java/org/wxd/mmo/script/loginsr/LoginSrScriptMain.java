package org.wxd.mmo.script.loginsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxd.boot.ioc.Ioc;
import org.wxd.mmo.loginsr.LoginSrAppMain;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-02-02 16:25
 **/
public class LoginSrScriptMain {

    public static void main(String[] args) throws Exception {
        try {
            LoginSrAppMain.init();
            LoginSrAppMain.initScript(LoginSrScriptMain.class.getClassLoader());
            Ioc.start(true, 1, "传奇", "测试版");
        } catch (Throwable throwable) {
            Logger logger = LoggerFactory.getLogger("root");
            logger.error("启动异常", throwable);
            System.exit(1);
        }
    }

}
