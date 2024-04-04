package wxdgaming.mmo.gamesr;

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
import wxdgaming.boot.starter.batis.MysqlService;
import wxdgaming.boot.starter.batis.MysqlService1;
import wxdgaming.mmo.core.GameBase;

import java.io.File;
import java.net.URLClassLoader;

public class GameSrAppMain {

    public static void main(String[] args) {
        try {
            init();
            initScript();
            Starter.start(true, 1, "mmo-game", "测试版");
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
                GameSrAppMain.class,
                GameBase.class
        );

        MysqlService gameDb = Starter.curIocInjector().getInstance(MysqlService.class);
        gameDb.checkDataBase("wxdgaming.mmo");

        MysqlService1 loginDb = Starter.curIocInjector().getInstance(MysqlService1.class);

    }

    public static void initScript() throws Exception {
        File file = new File("./script.jar");
        ClassDirLoader classDirLoader;
        if (file.exists()) {
            classDirLoader = ClassDirLoader.bootLib(GameSrAppMain.class.getClassLoader(), file.getPath());
        } else {
            classDirLoader = new JavaCoderCompile()
                    .parentClassLoader(GameSrAppMain.class.getClassLoader())
                    .compilerJava("wxdgaming.mmo.gamesr-script/src/main/java")
                    .classLoader("wxdgaming.mmo.gamesr-script/target/classes");
            classDirLoader.addURL("wxdgaming.mmo.gamesr-script/src/main/resources");
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassDirLoader classLoader) {
        MessagePackage.loadMessageId_HashCode(classLoader, true, "wxdgaming.mmo");
        ReflectContext.Builder scripts = ReflectContext.Builder.of(classLoader, "wxdgaming.mmo.script.gamesr");
        Starter.createChildInjector(scripts.build());
    }

}