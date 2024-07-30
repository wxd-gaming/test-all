package wxdgaming.boot.springstarter.zz;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import wxdgaming.boot.springstarter.test.B2;

@Configuration
@AutoConfigureAfter(Z1Config.class)
@Order(1)
public class Z2Config implements Ordered {

    @Override public int getOrder() {
        return 1;
    }

    public Z2Config() {
        System.out.println("\n\n" + this.getClass() + "\n\n");
    }

    @Bean
    @Order(1)
    public B2 b2() {
        return new B2();
    }


}
