package org.example;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import scripts.TestApi;

import java.io.IOException;
import java.util.Map;

@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication/*spring 启动标记*/
public class Main {

    static ConfigurableApplicationContext applicationContext;
    static ConfigurableApplicationContext childContainer;

    public static void main(String[] args) throws IOException {

        applicationContext = SpringApplication.run(Main.class, args);

        //子容器
        childContainer = new AnnotationConfigApplicationContext("scripts");
        childContainer.setParent(applicationContext);
        childContainer.setApplicationStartup(applicationContext.getApplicationStartup());
        //从子容器中获取父容器中的Bean
        HelloController parentService = childContainer.getBean(HelloController.class);
        LoggerFactory.getLogger(Main.class).info("{}", parentService);
        //getBeansOfType无法获取到父容器中的Bean
        Map<String, TestApi> map = childContainer.getBeansOfType(TestApi.class);
        map.forEach((k, v) -> LoggerFactory.getLogger(Main.class).info("{} => {}", k, v));

        System.out.println("http://127.0.0.1:18081/test");

    }

}