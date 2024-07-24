package gvm.test.c;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NativeMain {

    public static void main(String[] args) throws Exception {
        System.setProperty("jdk.attach.allowAttachSelf", "false");
        System.out.println("=================start=================");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        classLoader = RemoteClassLoader.build(
                classLoader,
                "http://localhost/qj5/a.jar",
                "http://localhost/qj5/b.jar"
        );

        System.out.println(classLoader);
        List<String> strings = ReflectContext.jarResources();
        for (String string : strings) {
            Enumeration<URL> urls = classLoader.getResources(string);
            while (urls.hasMoreElements()) {
                URL next = urls.nextElement();
                System.out.println(next);
            }
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
        Thread.sleep(2000);
        Runtime.getRuntime().exit(0);
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