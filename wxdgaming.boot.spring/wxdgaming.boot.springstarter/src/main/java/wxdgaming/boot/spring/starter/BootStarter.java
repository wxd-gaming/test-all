package wxdgaming.boot.spring.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.spring.starter.config.SpringUtil;
import wxdgaming.boot.spring.starter.i.OnStart;

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
        SpringUtil
                .reflectContext()
                .withMethodAnnotated(OnStart.class)
                .sorted(SpringUtil.METHOD_COMPARATOR)
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
    }

    public static void reload() throws Exception {
        ClassDirLoader classDirLoader = new ClassDirLoader("wxdgaming.boot.springstarter/target/test-classes", BootStarter.class.getClassLoader());
        SpringUtil.loadClassLoader(classDirLoader, "wxdgaming.boot.springstarter.scripts");
    }

}