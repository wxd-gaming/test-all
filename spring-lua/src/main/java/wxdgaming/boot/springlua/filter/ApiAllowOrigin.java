package wxdgaming.boot.springlua.filter;

import io.netty.util.internal.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 过滤器，处理跨域
 *
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-08-01 08:45
 **/
@Slf4j
@Component
public class ApiAllowOrigin implements WebMvcConfigurer, HandlerInterceptor {

    public String currentUrl(HttpServletRequest request) {
        String scheme = request.getScheme();             // http
        String serverName = request.getServerName();     // hostname.com
        int serverPort = request.getServerPort();        // 80
        String contextPath = request.getContextPath();   // /mywebapp
        String servletPath = request.getServletPath();   // /servlet/MyServlet

        // Reconstruct original requesting URL
        StringBuilder url = new StringBuilder();
        url.append(scheme).append("://").append(serverName);

        // Include server port if it's not standard http/https port
        if (!((scheme.equals("http") && serverPort == 80) || (scheme.equals("https") && serverPort == 443))) {
            url.append(":").append(serverPort);
        }

        url.append(contextPath).append(servletPath);

        return url.toString();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
    }

    @Override public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        if (log.isDebugEnabled()) {
            StringBuilder stringBuilder = new StringBuilder("\n-------------------- 接口日志，开始 --------------------\n");
            stringBuilder.append(request.getMethod()).append(" ").append(request.getRequestURL().toString()).append(" ");
            // 用户IP
            String clientIp = request.getRemoteAddr();
            stringBuilder.append("client ip：").append(clientIp).append("\n");
            // 接口路径
            String path = request.getServletPath();
            stringBuilder.append("path：").append(path).append("\n");
            stringBuilder.append("content-type：").append(request.getHeader("content-type")).append("\n");
            if (StringUtil.length(request.getQueryString()) > 0) {
                stringBuilder.append("query-string：").append(request.getQueryString()).append("\n");
            }
            stringBuilder.append("-------------------- 接口日志，结束 --------------------\n");
            log.debug(stringBuilder.toString());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
