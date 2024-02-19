package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication/*spring 启动标记*/
public class Main {

    static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {

        applicationContext = SpringApplication.run(Main.class, args);

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new File("scripts/target/classes").toURI().toURL()}, Main.class.getClassLoader());
        Class<?> aClass = urlClassLoader.loadClass("scripts.ScriptMain");
        Method scriptInit = aClass.getMethod("scriptInit", ConfigurableApplicationContext.class);
        scriptInit.invoke(null, applicationContext);
    }

}