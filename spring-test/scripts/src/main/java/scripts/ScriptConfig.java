package scripts;

import jakarta.servlet.ServletRegistration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class ScriptConfig {

    //@Bean
    //public DispatcherServlet resourceServlet(ApplicationContext applicationContext) {
    //    System.out.println("init resourceServlet");
    //    System.out.println("------------111111--------");
    //    // 创建DispatcherServlet的实例
    //    DispatcherServlet dispatcherServlet = new DispatcherServlet();
    //
    //    // 设置servlet映射
    //    ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
    //    registration.setLoadOnStartup(1);
    //    registration.addMapping("/");
    //    return dispatcherServlet;
    //}

}
