package code;

import example.db.GraalvmUtil;

import java.net.URL;
import java.util.List;

public class Test2 {

    public static void main(String[] args) throws Exception {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        List<String> strings = GraalvmUtil.jarResources();
        for (String string : strings) {
            URL resource = contextClassLoader.getResource(string);
            System.out.println(string + " - " + resource);
        }
    }

}
