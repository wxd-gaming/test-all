package gvm.c;

import wxdgaming.boot.agent.system.ReflectContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {

        // RemoteClassLoader remoteClassLoader = RemoteClassLoader.build(
        //         Main.class.getClassLoader(),
        //         "http://127.0.0.1:19000/serverapi/s1/aabb/128493823/a-encrypted.jar",
        //         "http://127.0.0.1:19000/serverapi/s1/aabb/128493823/b-encrypted.jar"
        // );
        ClassLoader classLoader = Main.class.getClassLoader();
        ReflectContext.Builder
                .of(classLoader, "gvm").build()
                .classStream()
                .forEach(System.out::println);

        System.out.println(classLoader);

        Set<Class<?>> classes = SubstrateLoadedClassTable.getLoadedClasses();
        for (Class<?> clazz : classes) {
            if (!clazz.getName().startsWith("java.") && !clazz.getName().startsWith("sun.") && !clazz.getName().startsWith("com.oracle")) {
                System.out.println(clazz.getName());
            }
        }

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