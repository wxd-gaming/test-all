package gvm.c;

import wxdgaming.boot.agent.loader.RemoteClassLoader;
import wxdgaming.boot.agent.system.ReflectContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {

        RemoteClassLoader remoteClassLoader = RemoteClassLoader.build(
                Main.class.getClassLoader(),
                "http://127.0.0.1:19000/serverapi/s1/aabb/128493823/a-encrypted.jar",
                "http://127.0.0.1:19000/serverapi/s1/aabb/128493823/b-encrypted.jar"
        );

        ReflectContext.Builder
                .of(remoteClassLoader, "gvm").build()
                .classStream()
                .forEach(System.out::println);

        System.out.println(remoteClassLoader);

        Class<?> aClass = remoteClassLoader.loadClass("gvm.b.Main");
        System.out.println(aClass.getClassLoader().hashCode());
        System.out.println(aClass.hashCode());

        Method point = aClass.getMethod("point");
        point.invoke(aClass.getConstructor().newInstance());
        System.out.println("敲入回车关闭：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();

    }

}