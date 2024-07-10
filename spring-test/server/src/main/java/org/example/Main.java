package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@ConfigurationPropertiesScan
@SpringBootApplication/*spring 启动标记*/
public class Main {

    static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) throws Exception {
        applicationContext = SpringApplication.run(Main.class, args);
        // ClassDirLoader classDirLoader = new ClassDirLoader("scripts/target/classes", Main.class.getClassLoader());
        // Class<?> aClass = classDirLoader.loadClass("scripts.ScriptMain");
        // Method scriptInit = aClass.getMethod("scriptInit", ConfigurableApplicationContext.class, ClassDirLoader.class);
        // scriptInit.invoke(null, applicationContext, classDirLoader);
    }

}