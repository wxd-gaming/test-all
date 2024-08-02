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
@Order(10)
@Component
@RequestMapping("/**")
public class AuthFilter implements BastFilter {

    @Override public void filter(InterceptorRegistration registration) {

    }
}
