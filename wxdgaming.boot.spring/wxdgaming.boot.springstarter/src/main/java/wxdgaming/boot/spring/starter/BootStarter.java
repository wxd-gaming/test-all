package wxdgaming.boot.spring.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import wxdgaming.boot.agent.loader.ClassDirLoader;
import wxdgaming.boot.agent.system.ReflectContext;
import wxdgaming.boot.spring.a.SpringUtil;

import java.util.Optional;

@EnableAsync
@EnableScheduling
@ConfigurationPropertiesScan
@SpringBootApplication(
        scanBasePackages = {
                "wxdgaming.boot.spring.a",
        },
        exclude = {
                DataSourceAutoConfiguration.class,
                MongoAutoConfiguration.class
        }
)
public class BootStarter {

    private static final Logger log = LoggerFactory.getLogger(BootStarter.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStarter.class, args);
        // reload();

        // SpringUtil.loadClassLoader(BootStarter.class.getClassLoader(), "wxdgaming.boot.spring.starter");
        ApplicationContext subContext = load(
                SpringUtil.getApplicationContext(),
                BootStarter.class.getClassLoader(),
                BootStarter.class.getPackageName()
        );
        // SpringUtil.setSubApplicationContext(subContext);
        ClassDirLoader classDirLoader = new ClassDirLoader(
                "wxdgaming.boot.springstarter/target/test-classes",
                BootStarter.class.getClassLoader()
        );
        load(
                subContext,
                classDirLoader,
                "wxdgaming.boot.springstarter.scripts"
        );
    }

    public static void reload() throws Exception {
        ClassDirLoader classDirLoader = new ClassDirLoader("wxdgaming.boot.springstarter/target/test-classes", BootStarter.class.getClassLoader());
        SpringUtil.loadClassLoader(classDirLoader, "wxdgaming.boot.springstarter.scripts");

        // AnnotationConfigApplicationContext subContext = load(
        //         SpringUtil.curApplicationContext(),
        //         classDirLoader,
        //         "wxdgaming.boot.springstarter.scripts"
        // );
    }

    public static ApplicationContext load(ApplicationContext parentContext, ClassLoader classLoader, String... packages) {
        AnnotationConfigWebApplicationContext subContext = new AnnotationConfigWebApplicationContext();
        subContext.setParent(parentContext);
        ReflectContext.Builder
                .of(classLoader, packages)
                .build()
                .classStream()
                .filter(SpringUtil::hasSpringAnnotation)
                .sorted((o1, o2) -> {
                    int o1Annotation = Optional.ofNullable(o1.getAnnotation(Order.class)).map(Order::value).orElse(999999);
                    int o2Annotation = Optional.ofNullable(o2.getAnnotation(Order.class)).map(Order::value).orElse(999999);
                    if (o1Annotation != o2Annotation) {
                        return Integer.compare(o1Annotation, o2Annotation);
                    }
                    return o1.getName().compareTo(o2.getName());
                })
                .forEach(clazz -> {
                    log.debug("register bean: {}", clazz);
                    subContext.register(clazz);
                });
        subContext.refresh();
        return subContext;
    }

}