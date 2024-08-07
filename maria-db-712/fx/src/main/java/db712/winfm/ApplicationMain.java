package db712.winfm;

import db712.server.*;
import javafx.application.Application;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ApplicationMain {

    public static void main(String[] args) throws Exception {

        LogbackResetTimeFilter.out = true;
        if (args.length > 0) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            List<String> strings = GraalvmUtil.jarResources();
            for (String string : strings) {
                URL resource = contextClassLoader.getResource(string);
                System.out.println(string + " - " + resource);
            }

            ReflectAction reflectAction = ReflectAction.of();
            reflectAction.action(MyDB.class, false);
            reflectAction.action(DBFactory.class, false);
            reflectAction.action(HelloApplication.class, false);
            reflectAction.action(HelloController.class, false);

            CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(10_000);
                    DBFactory.getIns().stop();
                    Runtime.getRuntime().halt(0);
                } catch (Exception ignore) {}
            });
        }

        Application.launch(HelloApplication.class);

    }
}