package org.wxd.mmo.loginsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxd.boot.agent.loader.ClassDirLoader;
import org.wxd.boot.agent.loader.JavaCoderCompile;
import org.wxd.boot.agent.system.ReflectContext;
import org.wxd.boot.net.message.MessagePackage;
import org.wxd.boot.starter.Starter;
import org.wxd.boot.system.JvmUtil;
import org.wxd.mmo.login.BeanBase;

import java.io.File;

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

        JvmUtil.setLogbackConfig();
        JvmUtil.setProperty("jks_path", "xiaw-jks/8227259__xiaw.net.jks");
        JvmUtil.setProperty("jks_pwd", "gmB8I91V");

        Starter.startBoot(
                LoginSrAppMain.class,
                BeanBase.class,
                org.wxd.mmo.common.cache.BeanBase.class
        );

    }

    public static void initScript() throws Exception {
        File file = new File("./script.jar");
        ClassDirLoader classDirLoader;
        if (file.exists()) {
            classDirLoader = ClassDirLoader.bootLib(LoginSrAppMain.class.getClassLoader(), file.getPath());
        } else {
            classDirLoader = new JavaCoderCompile()
                    .parentClassLoader(LoginSrAppMain.class.getClassLoader())
                    .compilerJava("org.wxd.mmo.loginsr-script/src/main/java")
                    .builderClassLoader();
            classDirLoader.addURL("org.wxd.mmo.loginsr-script/src/main/resources");
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        MessagePackage.loadMessageId_HashCode(classLoader, true, "org.wxd.mmo.script.game.proto.login.message");
        ReflectContext.Builder scripts = ReflectContext.Builder.of(classLoader, "org.wxd.mmo.script.loginsr");
        Starter.createChildInjector(scripts.build());
    }

}