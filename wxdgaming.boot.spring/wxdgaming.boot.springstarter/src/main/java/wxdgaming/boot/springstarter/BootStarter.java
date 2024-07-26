package wxdgaming.boot.springstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.boot.agent.loader.ClassDirLoader;

@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication/*spring 启动标记*/
public class BootStarter {

    public static void main(String[] args) throws Exception {
        IocContext.setApplicationContext(SpringApplication.run(BootStarter.class, args));
        reload();
    }

    public static void reload() throws Exception {
        ClassDirLoader classDirLoader = new ClassDirLoader("wxdgaming.boot.springstarter/target/test-classes", BootStarter.class.getClassLoader());
        IocContext.loadClassLoader(classDirLoader, "wxdgaming.boot.springstarter.scripts");
    }

}