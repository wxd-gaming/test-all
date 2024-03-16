package wxd.member.server;

import org.slf4j.LoggerFactory;
import wxdgaming.boot.agent.LogbackUtil;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.agent.loader.JavaCoderCompile;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.core.system.JvmUtil;
import wxdgaming.boot.starter.Starter;

import java.io.File;
import java.util.Collection;

/**
 * 启动主类
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-05-22 20:09
 **/
public class AppMain {

    public static void main(String[] args) throws Exception {
        try {
            LogbackUtil.setLogbackConfig();

            JvmUtil.setProperty(JvmUtil.Netty_Boss_Thread_Size, 2);
            JvmUtil.setProperty(JvmUtil.Netty_Work_Thread_Size, 6);

            JvmUtil.setProperty(JvmUtil.Default_Executor_Core_Size, 2);
            JvmUtil.setProperty(JvmUtil.Default_Executor_Max_Size, 4);

            JvmUtil.setProperty(JvmUtil.Logic_Executor_Core_Size, 6);
            JvmUtil.setProperty(JvmUtil.Logic_Executor_Max_Size, 12);
            JvmUtil.setProperty(JvmUtil.Netty_Idle_Time_Http_Server, 20);

            /*html*/
            Starter.startBoot(AppMain.class);
            loadLogic();
            Starter.start(true, 1, "1", "1.0.2");
        } catch (Throwable throwable) {
            LoggerFactory.getLogger(AppMain.class).error("启动失败", throwable);
            System.exit(1);
        }
    }

    public static void loadLogic() throws Exception {

        File file = new File("./logic.jar");
        ClassDirLoader classDirLoader;
        if (file.exists()) {
            classDirLoader = ClassDirLoader.bootLib(AppMain.class.getClassLoader(), file.getPath());
        } else {
            classDirLoader = new JavaCoderCompile()
                    .parentClassLoader(AppMain.class.getClassLoader())
                    .compilerJava("wxd.member.server-logic/src/main/java")
                    .builderClassLoader();
            classDirLoader.addURL("wxd.member.server-logic/src/main/resources");
        }
        ReflectContext.Builder scripts = ReflectContext.Builder.of(classDirLoader, "wxd.member.server.logic");
        Starter.createChildInjector(scripts.build());

    }

}
