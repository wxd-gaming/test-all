package example;

import db.server.DBFactory;
import db.server.GraalvmUtil;
import db.server.LogbackResetTimeFilter;
import javafx.application.Application;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MainApplication {

    public static void main(String[] args) throws Exception {
        LogbackResetTimeFilter.out = true;
        if (args.length > 0) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            List<String> strings = GraalvmUtil.jarResources();
            for (String string : strings) {
                URL resource = contextClassLoader.getResource(string);
                System.out.println(string + " - " + resource);
            }

            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(30_000);
                    DBFactory.getIns().stop();
                    Runtime.getRuntime().halt(0);
                } catch (Exception ignore) {}
            });
        }

        Application.launch(HelloApplication.class);

    }
}