package wxdgaming.boot.spring.starter.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import wxdgaming.boot.spring.starter.core.BastFilter;

/***
 * 权限过滤器，拦截器
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-30 13:30
 */
@Slf4j
@Order(1)
@Component
@RequestMapping("/s")
public class OtherFilter implements BastFilter {

    @Override public void filter(InterceptorRegistration registration) {

    }

    @Override public boolean preHandle(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Object handler) throws Exception {
        log.info("{} {} {}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        return BastFilter.super.preHandle(request, response, handler);
    }

    @Override public void postHandle(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Object handler,
                                     ModelAndView modelAndView) throws Exception {
        log.info("{} {} {}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        BastFilter.super.postHandle(request, response, handler, modelAndView);
    }

    @Override public void afterCompletion(HttpServletRequest request,
                                          HttpServletResponse response,
                                          Object handler,
                                          Exception ex) throws Exception {
        log.info("{} {} {}", request.getMethod(), request.getRequestURI(), request.getQueryString());
        BastFilter.super.afterCompletion(request, response, handler, ex);
    }
}
