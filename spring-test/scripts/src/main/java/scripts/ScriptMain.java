package scripts;

import org.example.HelloController;
import org.example.SpringAnnotationUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.wxd.boot.agent.loader.ClassDirLoader;
import org.wxd.boot.agent.system.ReflectContext;

import java.util.HashSet;
import java.util.Set;

public class ScriptMain {

    static AnnotationConfigApplicationContext childContainer;

    public static void scriptInit(ConfigurableApplicationContext context, ClassDirLoader classDirLoader) {
        //子容器
        childContainer = new AnnotationConfigApplicationContext();
        childContainer.setParent(context);
        childContainer.setClassLoader(classDirLoader);

        childContainer.scan(ScriptMain.class.getPackageName());
        childContainer.refresh();
        childContainer.start();
//        scriptInit0(context, classDirLoader);

        //从子容器中获取父容器中的Bean
        HelloController parentService = childContainer.getBean(HelloController.class);
        LoggerFactory.getLogger(ScriptMain.class).info("{}", parentService);
        //getBeansOfType无法获取到父容器中的Bean
        childContainer.getBeansOfType(TestApi.class).forEach((k, v) -> {
            LoggerFactory.getLogger(ScriptMain.class).info("{}", v);
        });
        System.out.println("http://127.0.0.1:18081/test");
    }

    public static void scriptInit0(ConfigurableApplicationContext context, ClassDirLoader classDirLoader) {

        Set<Class<?>> initBeanClass = new HashSet<>();
        ReflectContext reflectContext = ReflectContext.Builder.of(classDirLoader, ScriptMain.class.getPackageName()).build();
        // 获取beanFactory
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
        reflectContext.classStream().forEach(clazz -> {
            final String className = clazz.getName();
            // 2. 将有@spring注解的类交给spring管理
            // 2.1 判断是否注入spring
            boolean flag = SpringAnnotationUtils.hasSpringAnnotation(clazz);
            if (flag) {
                // 2.2交给spring管理
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
                // 此处beanName使用全路径名是为了防止beanName重复
                String packageName = className.substring(0, className.lastIndexOf(".") + 1);
                String beanName = className.substring(className.lastIndexOf(".") + 1);
                beanName = packageName + beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                // 2.3注册到spring的beanFactory中
                beanFactory.registerBeanDefinition(beanName, beanDefinition);
                // 2.4允许注入和反向注入
                beanFactory.autowireBean(clazz);
                beanFactory.initializeBean(clazz, beanName);
                    /*if(Arrays.stream(clazz.getInterfaces()).collect(Collectors.toSet()).contains(InitializingBean.class)){
                        initBeanClass.add(clazz);
                    }*/
                initBeanClass.add(clazz);
                System.out.println(clazz.getName());
            }
        });


    }

}
