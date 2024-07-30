package springmariadbserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class SpringMariaDbServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringMariaDbServerApplication.class, args);
        DBService dbService = applicationContext.getBean(DBService.class);
        Runtime.getRuntime().addShutdownHook(new Thread(dbService::stop));
    }

}
