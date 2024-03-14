package org.wxd.mmo.gamesr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxd.boot.agent.LogbackUtil;
import org.wxd.boot.agent.loader.ClassDirLoader;
import org.wxd.boot.agent.loader.JavaCoderCompile;
import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.core.system.JvmUtil;
import org.wxd.boot.net.message.MessagePackage;
import org.wxd.boot.starter.Starter;
import org.wxd.boot.starter.batis.MysqlService;
import org.wxd.boot.starter.batis.MysqlService1;
import org.wxd.mmo.core.GameBase;

import java.io.File;

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
        gameDb.checkDataBase("org.wxd.mmo");

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
                    .compilerJava("org.wxd.mmo.gamesr-script/src/main/java")
                    .builderClassLoader();
            classDirLoader.addURL("org.wxd.mmo.gamesr-script/src/main/resources");
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        MessagePackage.loadMessageId_HashCode(classLoader, true, "org.wxd.mmo");
        ReflectContext.Builder scripts = ReflectContext.Builder.of(classLoader, "org.wxd.mmo.script.gamesr");
        Starter.createChildInjector(scripts.build());
    }

}