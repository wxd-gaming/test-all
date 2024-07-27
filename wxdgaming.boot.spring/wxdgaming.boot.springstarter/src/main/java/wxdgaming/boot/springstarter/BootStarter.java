package wxdgaming.boot.springstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.springstarter.batis.MysqlService;
import wxdgaming.boot.springstarter.batis.RedisService;
import wxdgaming.boot.springstarter.config.threading.ThreadPoolConfig;

@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MongoAutoConfiguration.class
})
public class BootStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStarter.class, args);
        reload();
        System.out.println(SpringContext.getBean(MysqlService.class));
        SpringContext.getBean(RedisService.class).hset("ddd", "1", "1");
    }

    public static void reload() throws Exception {
        ClassDirLoader classDirLoader = new ClassDirLoader("wxdgaming.boot.springstarter/target/test-classes", BootStarter.class.getClassLoader());
        SpringContext.loadClassLoader(classDirLoader, "wxdgaming.boot.springstarter.scripts");
    }

}