package gvm.c;


import gvm.a.ReflectContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("jdk.attach.allowAttachSelf", "false");
        System.out.println("=================start=================");
        ClassLoader classLoader = Main.class.getClassLoader();

        // classLoader = RemoteClassLoader.build(
        //         Main.class.getClassLoader(),
        //         "http://localhost/qj5/a.jar",
        //         "http://localhost/qj5/b.jar"
        // );

        ReflectContext.Builder gvm = ReflectContext.Builder.of(classLoader, "gvm");
        ArrayList<String> resources = gvm.getResources();
        gvm
                .build()
                .classStream()
                .forEach(c -> System.out.println("ReflectContext：" + c));

        System.out.println(classLoader);

        Class<?> aClass = classLoader.loadClass("gvm.b.Main");
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