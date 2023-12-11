package org.wxd.mmo.gamesr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxd.agent.loader.ClassDirLoader;
import org.wxd.agent.loader.JavaCoderCompile;
import org.wxd.agent.system.ReflectContext;
import org.wxd.boot.batis.mongodb.MongoDataHelper;
import org.wxd.boot.ioc.Ioc;
import org.wxd.boot.net.message.MessagePackage;
import org.wxd.boot.system.JvmUtil;
import org.wxd.mmo.game.BeanBase;

import java.io.File;

public class GameSrAppMain {

    public static void main(String[] args) {
        try {
            init();
            initScript();
            Ioc.start(true, 1, "mmo-game", "测试版");
        } catch (Throwable throwable) {
            Logger logger = LoggerFactory.getLogger("root");
            logger.error("启动异常", throwable);
            System.exit(1);
        }
    }

    public static void init() throws Exception {

        JvmUtil.setLogbackConfig();
        JvmUtil.setProperty("jks_path", "xiaw-jks/8227259__xiaw.net.jks");
        JvmUtil.setProperty("jks_pwd", "gmB8I91V");
        Ioc.startBoot(
                GameSrAppMain.class,
                BeanBase.class,
                org.wxd.mmo.common.cache.BeanBase.class
        );

        MongoDataHelper bean = Ioc.getBean(MongoDataHelper.class);
        bean.checkDataBase("org.wxd.mmo.gamesr");

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
        Ioc.createChildInjector(scripts.build());
    }

}