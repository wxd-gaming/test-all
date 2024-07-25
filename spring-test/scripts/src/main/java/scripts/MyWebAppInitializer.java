// package scripts;
//
// import jakarta.servlet.ServletContext;
// import jakarta.servlet.ServletRegistration;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.WebApplicationInitializer;
// import org.springframework.web.servlet.DispatcherServlet;
//
// @Configuration
// public class MyWebAppInitializer implements WebApplicationInitializer {
//
//     @Override
//     public void onStartup(ServletContext servletContext) {
//         System.out.println("------------111111--------");
//         // 创建DispatcherServlet的实例
//         DispatcherServlet dispatcherServlet = new DispatcherServlet();
//
//         // 设置servlet映射
//         ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
//         registration.setLoadOnStartup(1);
//         registration.addMapping("/");
//     }
// }
