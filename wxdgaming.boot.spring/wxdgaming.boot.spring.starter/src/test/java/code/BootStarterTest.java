package code;

import b.BConfig;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * 测试启动器
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-30 20:50
 **/
@Slf4j
@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class}
)
public class BootStarterTest {

    static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        applicationContext = SpringApplication.run(BootStarterTest.class);
    }

    public static void load() {
        AnnotationConfigServletWebApplicationContext subContext = new AnnotationConfigServletWebApplicationContext();
        subContext.setParent(applicationContext);
        subContext.setEnvironment(applicationContext.getEnvironment());
        subContext.setApplicationStartup(applicationContext.getApplicationStartup());
        subContext.setServletContext(applicationContext.getBean(ServletContext.class));
        // subContext.setConfigLocation(BConfig.class.getPackageName());
        subContext.register(BConfig.class);
        subContext.refresh();
    }


}
