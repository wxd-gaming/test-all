package wxdgaming.boot.spring.core;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wxdgaming.boot.agent.LogbackUtil;

/**
 * 过滤器
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-30 13:59
 **/
public interface BastFilter extends WebMvcConfigurer, HandlerInterceptor {

    @Override
    default void addInterceptors(InterceptorRegistry registry) {
        try {
            RequestMapping annotation = this.getClass().getAnnotation(RequestMapping.class);
            String[] value = annotation.value();
            String string = value[0];
            InterceptorRegistration interceptorRegistration = registry.addInterceptor(this).addPathPatterns(string);
            LogbackUtil.logger().info("{} addPathPatterns {}", this.getClass().getSimpleName(), string);
            filter(interceptorRegistration);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    void filter(InterceptorRegistration registration);

}
