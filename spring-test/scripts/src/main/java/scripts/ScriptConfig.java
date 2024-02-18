package scripts;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ScriptConfig {

    @Bean
    public ServletRegistrationBean resourceServlet(ApplicationContext applicationContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet);
        //指定urlmapping
        servletRegistrationBean.addUrlMappings("/test");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setName("resource");
        return servletRegistrationBean;
    }

}
