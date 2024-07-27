package wxdgaming.boot.springstarter.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.Serializable;

/**
 * 忽略 url 大小写问题
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2023-11-24 11:45
 */
@Configuration
public class EasyWebUrlConfig implements Serializable, WebMvcConfigurer {

    private static final long serialVersionUID = 1L;

    /**
     * 访问路径大小写不敏感开启
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        // 配置url访问大小写不敏感
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }
}
