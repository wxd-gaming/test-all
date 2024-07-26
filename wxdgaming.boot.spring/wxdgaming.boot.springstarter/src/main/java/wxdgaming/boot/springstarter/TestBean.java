package wxdgaming.boot.springstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-26 11:19
 **/
@Configuration
public class TestBean {

    @Bean()
    public B1 configBean() {
        return new B1();
    }

    public static class B1 {
        private int b = 1;
    }

    public static class B2 {
        private int b = 1;
    }

}
