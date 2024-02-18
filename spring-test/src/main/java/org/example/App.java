package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import scripts.TestApi;

import java.util.Map;

/**
 * 启动
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-02-18 09:32
 **/
@Slf4j
@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication/*spring 启动标记*/
public class App {
    public static void main(String[] args) {
        //父容器
        ApplicationContext parentContainer = new AnnotationConfigApplicationContext(App.class);
        //子容器
        ConfigurableApplicationContext childContainer = new AnnotationConfigApplicationContext(TestApi.class);
        childContainer.setParent(parentContainer);
        //从子容器中获取父容器中的Bean
        HelloController parentService = childContainer.getBean(HelloController.class);
        log.info("{}", parentService);
        //getBeansOfType无法获取到父容器中的Bean
        Map<String, TestApi> map = childContainer.getBeansOfType(TestApi.class);
        map.forEach((k, v) -> log.info("{} => {}", k, v));
    }
}
