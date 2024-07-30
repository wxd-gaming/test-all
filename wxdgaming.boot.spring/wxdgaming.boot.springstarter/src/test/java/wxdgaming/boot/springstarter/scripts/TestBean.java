package wxdgaming.boot.springstarter.scripts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author: wxd-gaming(無心道, 15388152619)
 * @version: 2024-07-26 11:19
 **/
@Order(1)
@Configuration
public class TestBean {

    @Bean
    public TB1 tb1() {
        return new TB1();
    }

    public static class TB1 {
        public TB1() {
            System.out.println("\n" + this.getClass());
        }
    }
}
