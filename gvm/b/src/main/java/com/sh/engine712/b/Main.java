package com.sh.engine712.b;

public class Main {

    public static void main(String[] args) {
        com.sh.engine712.a.Main.print();
        ClassLoader classLoader = Main.class.getClassLoader();
        ReflectContext.Builder
                .of(classLoader, "com.sh.engine712").build()
                .classStream()
                .forEach(System.out::println);
        System.out.println(classLoader);
    }


}