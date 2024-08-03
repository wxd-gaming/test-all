package wxdgaming.boot.spring.data;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;


@Configuration
public class ManagerFactory {

    @Resource(name = "dsOne") DataSource dsOne;

    @Resource JpaProperties jpaProperties;


    // @Bean
    // @Primary
    // LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
    //     return builder
    //             .dataSource(dsOne)
    //             .properties(jpaProperties.getProperties())
    //             .packages("wxdgaming.boot.spring.data")
    //             .persistenceUnit("pu1")
    //             .build();
    // }

}
