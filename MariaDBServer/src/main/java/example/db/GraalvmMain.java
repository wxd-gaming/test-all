package example.db;

import java.net.URL;
import java.util.List;

public class GraalvmMain {

    public static void main(String[] args) throws Exception {
        DBFactory.getIns().init();
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        List<String> strings = GraalvmUtil.jarResources();
        for (String string : strings) {
            URL resource = contextClassLoader.getResource(string);
            System.out.println(string + " - " + resource);
        }
        System.out.println("====================================================");
        System.out.println("");
        System.out.println("数据库服务器启动完成  - " + "127.0.0.1:" + DBFactory.getIns().getMyDB().props.getProperty("port") + "/" + DBFactory.getIns().getMyDB().props.getProperty("database"));
        System.out.println("敲回车键可以关闭服务");
        System.out.println("");
        System.out.println("====================================================");

        Thread addShutdownHook = new Thread(() -> {
            DBFactory.getIns().stop();
        });
        Thread.sleep(30_000);
        addShutdownHook.run();
        Runtime.getRuntime().halt(0);
    }

}
