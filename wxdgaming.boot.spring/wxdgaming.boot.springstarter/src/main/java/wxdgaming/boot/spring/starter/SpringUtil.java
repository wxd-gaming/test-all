package wxdgaming.boot.spring.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import wxdgaming.boot.agent.system.ReflectContext;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * spring 工具
 *
 * @author: Troy.Chen(無心道, 15388152619)
 * @version: 2024-07-30 14:52
 */
@Slf4j
@Configuration
public class SpringUtil implements IBaseOrder, BeanDefinitionRegistryPostProcessor {

    /** 上下文对象实例 */
    private static ConfigurableApplicationContext applicationContext;
    private static BeanDefinitionRegistry beanDefinitionRegistry;

    /** 获取applicationContext */
    public static ConfigurableApplicationContext getApplicationContext() {
        return SpringUtil.applicationContext;
    }

    /** d */
    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return SpringUtil.beanDefinitionRegistry;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name 参数传入要获取的实例的类名 首字母小写，这是默认的
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 注册一个bean
     *
     * @param beanClass bean 类
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-07-26 17:30
     */
    public static void registerBean(Class<?> beanClass) {
        registerBean(beanClass.getName(), beanClass);
    }

    /**
     * 注册一个bean
     *
     * @param beanClass bean 类
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-07-26 17:30
     */
    public static void registerBean(String name, Class<?> beanClass) {
        registerBean(name, beanClass, true);
    }

    public static void registerBean(String name, Class<?> beanClass, boolean removeOld) {

        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        // 将有@spring注解的类交给spring管理
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
        // 设置当前bean定义对象是单利的
        beanDefinition.setScope("singleton");

        if (removeOld && defaultListableBeanFactory.containsBeanDefinition(name)) {
            defaultListableBeanFactory.removeBeanDefinition(name);
        }

        // 获取bean工厂并转换为DefaultListableBeanFactory
        defaultListableBeanFactory.registerBeanDefinition(name, beanDefinition);

        log.debug("register bean {}, {}", name, beanClass);
    }

    /**
     * 注册一个实例对象
     *
     * @param instance 对象
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-07-26 17:30
     */
    public static void registerInstance(Object instance) {
        registerInstance(instance.getClass().getName(), instance);
    }

    /**
     * 注册一个实例对象
     *
     * @param name     对象名，beanName
     * @param instance 对象实例
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-07-26 17:30
     */
    public static <T> void registerInstance(String name, T instance) {
        registerInstance(name, instance, true);
    }

    public static <T> void registerInstance(String name, T instance, boolean removeOld) {
        // 获取bean工厂并转换为DefaultListableBeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        if (removeOld && defaultListableBeanFactory.containsBean(name)) {
            defaultListableBeanFactory.removeBeanDefinition(name);
        }
        defaultListableBeanFactory.registerSingleton(name, instance);

        log.debug("register instance {}, {}", name, instance.getClass().getName());
    }

    /**
     * 判断一个类是否有 Spring 核心注解
     *
     * @param clazz 要检查的类
     * @return true 如果该类上添加了相应的 Spring 注解；否则返回 false
     */
    public static boolean hasSpringAnnotation(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        // 是否是接口
        if (clazz.isInterface()) {
            return false;
        }
        // 是否是抽象类
        if (Modifier.isAbstract(clazz.getModifiers())) {
            return false;
        }

        try {
            if (
                    clazz.getAnnotation(Configuration.class) != null ||
                            clazz.getAnnotation(Service.class) != null ||
                            clazz.getAnnotation(Component.class) != null ||
                            clazz.getAnnotation(Repository.class) != null ||
                            clazz.getAnnotation(Controller.class) != null ||
                            clazz.getAnnotation(RestController.class) != null
            ) {
                return true;
            }
        } catch (Exception e) {
            log.error("出现异常：{}", e.getMessage());
        }
        return false;
    }

    /**
     * 从 classLoader 入手 动态注入
     *
     * @param classLoader classLoader
     * @param packages    需要加载的包名
     * @author: Troy.Chen(無心道, 15388152619)
     * @version: 2024-07-25 20:48
     */
    public static void loadClassLoader(ClassLoader classLoader, String... packages) {
        List<Class<?>> list = ReflectContext.Builder.of(classLoader, packages)
                .build().classStream()
                .filter(SpringUtil::hasSpringAnnotation)
                .toList();

        for (Class<?> clazz : list) {
            registerBean(clazz);
        }

        for (Class<?> clazz : list) {
            registerController(clazz.getName());
        }

    }

    /**
     * 注册Controller
     *
     * @param controllerBeanName 新注册的
     */
    public static void registerController(String controllerBeanName) {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        try {
            unregisterController(controllerBeanName);
        } catch (Throwable e) {
            log.error("unregister controllerBeanName={}", controllerBeanName, e);
        }
        try {
            // 注册Controller
            Method method = requestMappingHandlerMapping
                    .getClass()
                    .getSuperclass()
                    .getSuperclass().
                    getDeclaredMethod("detectHandlerMethods", Object.class);
            // 将private改为可使用
            method.setAccessible(true);
            method.invoke(requestMappingHandlerMapping, controllerBeanName);
            log.debug("register Controller {}", controllerBeanName);
        } catch (Throwable e) {
            log.error("register controllerBeanName={}", controllerBeanName, e);
        }
    }

    /**
     * 去掉Controller的Mapping
     *
     * @param controllerBeanName 需要卸载的服务
     */
    public static void unregisterController(String controllerBeanName) {
        final RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        final Object controller = applicationContext.getBean(controllerBeanName);
        final Class<?> targetClass = controller.getClass();
        ReflectionUtils.doWithMethods(
                targetClass,
                method -> {
                    try {
                        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
                        Method createMappingMethod = RequestMappingHandlerMapping.class.getDeclaredMethod(
                                "getMappingForMethod",
                                Method.class,
                                Class.class
                        );
                        createMappingMethod.setAccessible(true);
                        RequestMappingInfo requestMappingInfo = (RequestMappingInfo)
                                createMappingMethod.invoke(requestMappingHandlerMapping, specificMethod, targetClass);
                        if (requestMappingInfo != null) {
                            requestMappingHandlerMapping.unregisterMapping(requestMappingInfo);
                        }
                    } catch (Throwable e) {
                        log.error("unregister controllerBeanName={}", controllerBeanName, e);
                    }
                },
                ReflectionUtils.USER_DECLARED_METHODS
        );
    }

    @Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = (ConfigurableApplicationContext) applicationContext;
        log.info("register applicationContext");
    }

    @Override public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        SpringUtil.beanDefinitionRegistry = (registry);
        log.info("register beanDefinitionRegistry");
    }

}
