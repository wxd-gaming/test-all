package wxdgaming.mmo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wxdgaming.boot.agent.LogbackUtil;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.agent.loader.JavaCoderCompile;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.starter.Starter;

import java.io.File;

public class WebAppMain {

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
        Starter.startBoot(WebAppMain.class);

    }

    public static void initScript() throws Exception {
        File file = new File("./script.jar");
        ClassDirLoader classDirLoader;
        if (file.exists()) {
            classDirLoader = ClassDirLoader.bootLib(WebAppMain.class.getClassLoader(), file.getPath());
        } else {
            classDirLoader = new JavaCoderCompile()
                    .parentClassLoader(WebAppMain.class.getClassLoader())
                    .compilerJava("wxdgaming.mmo.web-script/src/main/java")
                    .builderClassLoader();
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        ReflectContext.Builder reflectBuilder = ReflectContext
                .Builder
                .of(
                        classLoader,
                        "wxdgaming.mmo.web.script"
                );
        Starter.createChildInjector(reflectBuilder.build());
    }

}