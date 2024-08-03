package wxdgaming.boot.spring.starter;

import boot.b.BScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.spring.data.DataScan;
import wxdgaming.boot.spring.starter.config.SpringUtil;
import wxdgaming.boot.spring.starter.i.OnStart;

@EnableAsync
@EnableScheduling
@EntityScan("wxdgaming.boot.spring.data")
@EnableJpaRepositories("wxdgaming.boot.spring.data")
@SpringBootApplication(
        scanBasePackageClasses = {BScan.class, BootScan.class, DataScan.class},
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class BootStarter {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStarter.class, args);
        // reload();
        SpringUtil
                .withMethodAnnotated(OnStart.class)
                .forEach(method -> {
                    Class<?> cls = method.getDeclaringClass();
                    Object bean = SpringUtil.getBean(cls);
                    System.out.println(cls + " - " + method.getName());
                    try {
                        method.invoke(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        // RedisService redisService = SpringUtil.getBean(RedisService.class);
        // String randomString = StringUtil.getRandomString(2048);
        // redisService.hset("ddss", randomString, String.valueOf(MyClock.millis()));
        // redisService.set(randomString, String.valueOf(MyClock.millis()), 15);
    }

    public static void reload() throws Exception {
        ClassDirLoader classDirLoader = new ClassDirLoader("wxdgaming.boot.springstarter/target/test-classes", BootStarter.class.getClassLoader());
        SpringUtil.loadClassLoader(classDirLoader, "wxdgaming.boot.springstarter.scripts");
    }

}