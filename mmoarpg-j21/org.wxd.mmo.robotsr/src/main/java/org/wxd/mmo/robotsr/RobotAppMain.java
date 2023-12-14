package org.wxd.mmo.robotsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxd.agent.loader.ClassDirLoader;
import org.wxd.agent.loader.JavaCoderCompile;
import org.wxd.agent.system.ReflectContext;
import org.wxd.boot.net.message.MessagePackage;
import org.wxd.boot.starter.Starter;
import org.wxd.boot.system.JvmUtil;
import org.wxd.mmo.login.BeanBase;

import java.io.File;

/**
 * 启动类
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-08-10 12:12
 **/
public class RobotAppMain {


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

        JvmUtil.setLogbackConfig();
        JvmUtil.setProperty("jks_path", "xiaw-jks/8227259__xiaw.net.jks");
        JvmUtil.setProperty("jks_pwd", "gmB8I91V");
        Starter.startBoot(
                RobotAppMain.class,
                BeanBase.class,
                org.wxd.mmo.common.cache.BeanBase.class
        );

    }

    public static void initScript() throws Exception {
        File file = new File("./script.jar");
        ClassDirLoader classDirLoader;
        if (file.exists()) {
            classDirLoader = ClassDirLoader.bootLib(RobotAppMain.class.getClassLoader(), file.getPath());
        } else {
            classDirLoader = new JavaCoderCompile()
                    .parentClassLoader(RobotAppMain.class.getClassLoader())
                    .compilerJava("org.wxd.mmo.robot-script/src/main/java")
                    .builderClassLoader();
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        MessagePackage.loadMessageId_HashCode(classLoader, true, "org.wxd.mmo");
        ReflectContext.Builder builder = ReflectContext.Builder.of(classLoader, "org.wxd.mmo.robot.script");
        Starter.createChildInjector(builder.build());
    }

}
