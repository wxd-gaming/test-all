package code;

import b.BConfig;
import b.MyDispatcherServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.WebApplicationContext;
import wxdgaming.boot.spring.a.SpringUtil;

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
        scanBasePackages = {"wxdgaming.boot.spring.a"},
        exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class}
)
public class BootStarterTest {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BootStarterTest.class, args);

        WebApplicationContext applicationContext1 = subContext(applicationContext);
        MyDispatcherServlet bean = applicationContext1.getBean(MyDispatcherServlet.class);
        bean.onRefresh(applicationContext1);
    }

    public static WebApplicationContext subContext(ApplicationContext parentContext) {
        AnnotationConfigServletWebApplicationContext subContext = new AnnotationConfigServletWebApplicationContext();
        subContext.setParent(parentContext);
        subContext.setServletContext(SpringUtil.getServletContext());
        subContext.register(BConfig.class);
        subContext.refresh();
        return subContext;
    }

}
