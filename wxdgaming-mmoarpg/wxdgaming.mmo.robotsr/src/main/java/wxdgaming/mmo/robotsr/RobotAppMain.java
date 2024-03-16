package wxdgaming.mmo.robotsr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wxdgaming.boot.agent.LogbackUtil;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.agent.loader.JavaCoderCompile;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.net.message.MessagePackage;
import wxdgaming.boot.starter.Starter;
import wxdgaming.mmo.core.common.cache.BeanBase;

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

        LogbackUtil.setLogbackConfig();
        JvmUtil.setProperty("jks_path", "xiaw-jks/8227259__xiaw.net.jks");
        JvmUtil.setProperty("jks_pwd", "gmB8I91V");
        Starter.startBoot(
                RobotAppMain.class,
                wxdgaming.mmo.core.login.BeanBase.class,
                BeanBase.class
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
                    .compilerJava("wxdgaming.mmo.robot-script/src/main/java")
                    .builderClassLoader();
        }
        initScript(classDirLoader);
    }

    public static void initScript(ClassLoader classLoader) {
        MessagePackage.loadMessageId_HashCode(classLoader, true, "wxdgaming.mmo");
        ReflectContext.Builder builder = ReflectContext.Builder.of(classLoader, "wxdgaming.mmo.robot.script");
        Starter.createChildInjector(builder.build());
    }

}
