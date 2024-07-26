package wxdgaming.boot.springstarter.threading;

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * web容器线程池
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-26 09:13
 **/
@Configuration
public class CustomNettyConfiguration implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {

    }

}
