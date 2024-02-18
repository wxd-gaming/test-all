package scripts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@RestController
public class TestApi {

    @RequestMapping(value = "/test")
    public String test() {
        return "ok";
    }

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
