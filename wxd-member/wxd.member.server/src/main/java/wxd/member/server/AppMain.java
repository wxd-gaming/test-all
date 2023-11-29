package wxd.member.server;

import org.slf4j.LoggerFactory;
import org.wxd.agent.loader.ClassDirLoader;
import org.wxd.agent.loader.JavaCoderCompile;
import org.wxd.ioc.Ioc;
import org.wxd.system.ClassUtil;
import org.wxd.system.JvmUtil;

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
            JvmUtil.setLogbackConfig();

            JvmUtil.setProperty(JvmUtil.Netty_Boss_Thread_Size, 2);
            JvmUtil.setProperty(JvmUtil.Netty_Work_Thread_Size, 6);

            JvmUtil.setProperty(JvmUtil.Default_Executor_Core_Size, 2);
            JvmUtil.setProperty(JvmUtil.Default_Executor_Max_Size, 4);

            JvmUtil.setProperty(JvmUtil.Logic_Executor_Core_Size, 6);
            JvmUtil.setProperty(JvmUtil.Logic_Executor_Max_Size, 12);
            JvmUtil.setProperty(JvmUtil.Netty_Idle_Time_Http_Server, 20);

            /*html*/
            Ioc.initBoot(AppMain.class);
            loadLogic();
            Ioc.start(true, 1, "1", "1.0.2");
        } catch (Throwable throwable) {
            LoggerFactory.getLogger(AppMain.class).error("启动失败", throwable);
            Ioc.printFail();
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

        Collection<Class> scripts = ClassUtil.getClasses(classDirLoader, "wxd.member.server.logic");
        Ioc.iocInitBean(scripts);

    }

}
