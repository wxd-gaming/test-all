package gvm.test.c;


import gvm.test.a.ReflectContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("jdk.attach.allowAttachSelf", "false");
        System.out.println("=================start=================");

        // classLoader = RemoteClassLoader.build(
        //         Main.class.getClassLoader(),
        //         "http://localhost/qj5/a.jar",
        //         "http://localhost/qj5/b.jar"
        // );

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);

        Enumeration<URL> urls = classLoader.getResources("gvm/test");
        while (urls.hasMoreElements()) {
            URL next = urls.nextElement();
            System.out.println(next);
        }

        ReflectContext.Builder gvm = ReflectContext.Builder.of(classLoader, "gvm.test");
        ArrayList<String> resources = gvm.getResources();
        gvm
                .build()
                .classStream()
                .forEach(c -> System.out.println("ReflectContext：" + c));


        Class<?> aClass = classLoader.loadClass("gvm.test.b.Main");
        if (aClass != null) {
            System.out.println(aClass.getClassLoader().hashCode());
            System.out.println(aClass.hashCode());

            Method point = aClass.getMethod("point");
            point.invoke(aClass.getConstructor().newInstance());
        }
        System.out.println("敲入回车关闭：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

    }

    public static void files(String split, File file) {
        System.out.println(split + file);
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                files(split + "-", f);
            }
        }
    }

}