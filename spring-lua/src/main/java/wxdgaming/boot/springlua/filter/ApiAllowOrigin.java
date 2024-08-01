package wxdgaming.boot.springlua.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 过滤器，处理跨域
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-01 08:45
 **/
@Component
public class ApiAllowOrigin implements HandlerInterceptor {

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
