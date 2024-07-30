package wxdgaming.boot.spring.starter.zz;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import wxdgaming.boot.spring.starter.test.B2;

@Configuration
@AutoConfigureAfter(Z1Config.class)
@Order(1)
public class Z2Config implements Ordered {

    @Override public int getOrder() {
        return 1;
    }

    public Z2Config() {
        System.out.println("\n" + this.getClass());
    }

    @Bean
    @Order(1)
    public B2 b2() {
        return new B2();
    }


}
