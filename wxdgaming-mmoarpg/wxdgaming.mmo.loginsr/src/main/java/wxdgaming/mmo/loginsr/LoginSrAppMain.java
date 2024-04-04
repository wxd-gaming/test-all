package wxdgaming.mmo.loginsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wxdgaming.boot.agent.LogbackUtil;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.agent.loader.ClassFileObjectLoader;
import wxdgaming.boot.agent.loader.JavaCoderCompile;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.net.message.MessagePackage;
import wxdgaming.boot.starter.Starter;
import wxdgaming.mmo.core.GameBase;

import java.io.File;
import java.net.URLClassLoader;

public class LoginSrAppMain {

    public static void main(String[] args) {
        try {
            init();
            initScript();
            Starter.start(true, 1, "mmo-login", "测试版");
        } catch (Throwable throwable) {
            Logger logger = LoggerFactory.getLogger("root");
            logger.error("启动异常", throwable);
            System.exit(1);
        }
    }

    public static void init() throws Exception {

        LogbackUtil.setLogbackConfig();
        JvmUtil.setProperty("jks_path", "xiaw-jks/8227259__xiaw.net.jks");
        JvmUtil.setProperty("jks_pwd", "gmB8I91V");

        Starter.startBoot(
                LoginSrAppMain.class,
                GameBase.class
        );

    }

    public static void initScript() throws Exception {
        File file = new File("./script.jar");
        ClassDirLoader classLoader;
        if (file.exists()) {
            classLoader = ClassDirLoader.bootLib(LoginSrAppMain.class.getClassLoader(), file.getPath());
        } else {

            classLoader = new JavaCoderCompile()
                    .parentClassLoader(LoginSrAppMain.class.getClassLoader())
                    .compilerJava("wxdgaming.mmo.loginsr-script/src/main/java")
                    .classLoader("wxdgaming.mmo.loginsr-script/target/classes");

            classLoader.addURL("wxdgaming.mmo.loginsr-script/src/main/resources");
        }
        initScript(classLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        MessagePackage.loadMessageId_HashCode(classLoader, true, "wxdgaming.mmo.script.game.proto.login.message");
        ReflectContext.Builder scripts = ReflectContext.Builder.of(classLoader, "wxdgaming.mmo.script.loginsr");
        Starter.createChildInjector(scripts.build());
    }

}