package wxdgaming.boot.spring.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.spring.starter.config.SpringUtil;

@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class BootStarter {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStarter.class, args);
        reload();
    }

    public static void reload() throws Exception {
        ClassDirLoader classDirLoader = new ClassDirLoader("wxdgaming.boot.springstarter/target/test-classes", BootStarter.class.getClassLoader());
        SpringUtil.loadClassLoader(classDirLoader, "wxdgaming.boot.springstarter.scripts");
    }

}