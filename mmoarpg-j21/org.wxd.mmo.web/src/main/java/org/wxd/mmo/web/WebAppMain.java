package org.wxd.mmo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wxd.agent.loader.ClassDirLoader;
import org.wxd.agent.loader.JavaCoderCompile;
import org.wxd.agent.system.ReflectBuilder;
import org.wxd.boot.ioc.Ioc;
import org.wxd.boot.system.JvmUtil;

import java.io.File;

public class WebAppMain {

    public static void main(String[] args) {
        try {
            init();
            initScript();
            Ioc.start(true, 1, "mmo-login", "测试版");
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
        Ioc.startBoot(WebAppMain.class);

    }

    public static void initScript() throws Exception {
        File file = new File("./script.jar");
        ClassDirLoader classDirLoader;
        if (file.exists()) {
            classDirLoader = ClassDirLoader.bootLib(WebAppMain.class.getClassLoader(), file.getPath());
        } else {
            classDirLoader = new JavaCoderCompile()
                    .parentClassLoader(WebAppMain.class.getClassLoader())
                    .compilerJava("org.wxd.mmo.web-script/src/main/java")
                    .builderClassLoader();
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        ReflectBuilder reflectBuilder = ReflectBuilder.of(classLoader, "org.wxd.mmo.web.script");
        Ioc.createChildInjector(reflectBuilder.build());
    }

}