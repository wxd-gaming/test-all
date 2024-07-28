package wxdgaming.boot.springstarter.zz;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import wxdgaming.boot.springstarter.test.B1;

@Configuration
@AutoConfigureBefore(Z2Config.class)
@Order(2)
public class Z1Config {

    public Z1Config() {
        System.out.println("\n\n" + this.getClass() + "\n\n");
    }

    @Bean
    @Order(2)
    public B1 b1(){
        return new B1();
    }

}
