package scripts;

import org.example.HelloController;
import org.example.Main;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class ScriptMain {

    static AnnotationConfigApplicationContext childContainer;

    public static void scriptInit(ConfigurableApplicationContext context) {
        //子容器
        childContainer = new AnnotationConfigApplicationContext("scripts");
        childContainer.setParent(context);
        //从子容器中获取父容器中的Bean
        HelloController parentService = childContainer.getBean(HelloController.class);
        LoggerFactory.getLogger(Main.class).info("{}", parentService);
        //getBeansOfType无法获取到父容器中的Bean
        Map<String, TestApi> map = childContainer.getBeansOfType(TestApi.class);
        map.forEach((k, v) -> LoggerFactory.getLogger(Main.class).info("{} => {}", k, v));

        System.out.println("http://127.0.0.1:18081/test");
    }

}
