package b;

import jakarta.servlet.MultipartConfigElement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
@Configuration
@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class}
)
public class BConfig {


    public BConfig() {
        log.info("BConfig");
    }


    @Autowired private WebMvcProperties webMvcProperties;
    @Autowired private MultipartConfigElement multipartConfig;

    @Bean
    @Primary
    public DispatcherServletRegistrationBean dispatcherServlet2(DispatcherServlet dispatcherServlet) {
        DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet, "/aaa/*");
        registration.setName("dispatcherServlet2");
        registration.setLoadOnStartup(this.webMvcProperties.getServlet().getLoadOnStartup());
        if (this.multipartConfig != null) {
            registration.setMultipartConfig(this.multipartConfig);
        }
        return registration;
    }

}
