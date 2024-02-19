package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
@Component
public class DynamicLoad {


    @Autowired
    private ApplicationContext applicationContext;

    private final Map<String, MyClassLoader> myClassLoaderCenter = new ConcurrentHashMap<>();

    /**
     * 动态加载指定路径下指定jar包
     *
     * @param path
     * @param fileName
     * @return map<jobHander, Cron> 创建xxljob任务时需要的参数配置
     */
    public void loadJar(String path, String fileName) throws Exception {}

    /**
     * 动态加载指定路径下指定jar包
     *
     * @param path
     * @param fileName
     * @return map<jobHander, Cron> 创建xxljob任务时需要的参数配置
     */
    public void loadJar(String path, String fileName, ClassLoader parentLoader) throws Exception {
        File file = new File(path + "/" + fileName);
        // 获取beanFactory
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        // 获取当前项目的执行器
        try {
            // URLClassloader加载jar包规范必须这么写
            URL url = new URL("jar:file:" + file.getAbsolutePath() + "!/");
            URLConnection urlConnection = url.openConnection();
            JarURLConnection jarURLConnection = (JarURLConnection) urlConnection;
            // 获取jar文件
            JarFile jarFile = jarURLConnection.getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();

            // 创建自定义类加载器，并加到map中方便管理
            MyClassLoader myClassloader = new MyClassLoader(new URL[]{url}, parentLoader);
            myClassLoaderCenter.put(fileName, myClassloader);
            Set<Class<?>> initBeanClass = new HashSet<>(jarFile.size());
            // 遍历文件
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    // 1. 加载类到jvm中
                    // 获取类的全路径名
                    String className = jarEntry.getName().replace('/', '.').substring(0, jarEntry.getName().length() - 6);
                    // 1.1进行反射获取
                    myClassloader.loadClass(className);
                }
            }
            Map<String, Class<?>> loadedClasses = myClassloader.getLoadedClasses();
            for (Map.Entry<String, Class<?>> entry : loadedClasses.entrySet()) {
                String className = entry.getKey();
                Class<?> clazz = entry.getValue();
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
                }
            }
            // spring bean实际注册
            initBeanClass.forEach(beanFactory::getBean);
        } catch (IOException e) {
            log.error("读取{} 文件异常", fileName);
            e.printStackTrace();
            throw new RuntimeException("读取jar文件异常: " + fileName);
        }
    }
}
