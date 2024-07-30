package wxdgaming.boot.spring.starter.zz;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import wxdgaming.boot.spring.starter.test.B1;

@Configuration
@AutoConfigureBefore(Z2Config.class)
@Order(2)
public class Z1Config implements Ordered {

    @Override public int getOrder() {
        return 10;
    }

    public Z1Config() {
        System.out.println("\n" + this.getClass());
    }

    @Bean
    @Order(2)
    public B1 b1(){
        return new B1();
    }

}
