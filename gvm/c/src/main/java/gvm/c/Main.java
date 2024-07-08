package gvm.c;


import gvm.a.ReflectContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("=================start=================");
        ClassLoader classLoader = Main.class.getClassLoader();

        // classLoader = RemoteClassLoader.build(
        //         Main.class.getClassLoader(),
        //         "http://localhost/qj5/a.jar",
        //         "http://localhost/qj5/b.jar"
        // );

        ReflectContext.Builder
                .of(classLoader, "gvm").build()
                .classStream()
                .forEach(c -> System.out.println("读取资源：" + c));

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

}