package fxdb.fxdb;

import fxdb.fxdb.service.GraalvmUtil;
import javafx.application.Application;

import java.net.URL;
import java.util.List;

public class MainApplication {

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            List<String> strings = GraalvmUtil.jarResources();
            for (String string : strings) {
                URL resource = contextClassLoader.getResource(string);
                System.out.println(string + " - " + resource);
            }
        }
        Application.launch(HelloApplication.class);
    }
}