package code;

import b.BConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

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
        scanBasePackageClasses = {BConfig.class},
        exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class}
)
public class BootStarterTest {
    public static void main(String[] args) throws Exception {
        AnnotationConfigServletWebServerApplicationContext subContext = new AnnotationConfigServletWebServerApplicationContext();
        subContext.register(BootStarterTest.class);
        subContext.refresh();
    }


}
