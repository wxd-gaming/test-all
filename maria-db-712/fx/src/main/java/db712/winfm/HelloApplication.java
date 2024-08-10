package db712.winfm;

import db712.server.DBFactory;
import db712.server.WebService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        WebService.getIns().setShowWindow(() -> {
            Platform.runLater(() -> {
                /*todo 必须要交给ui线程*/
                primaryStage.setIconified(false);
                primaryStage.show();
            });
        });
        Class<HelloApplication> helloApplicationClass = HelloApplication.class;
        URL resource = helloApplicationClass.getResource("hello-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);

        Scene scene = new Scene(fxmlLoader.load(), Color.BLACK);
        primaryStage.setTitle("qj-712-db-server");
        primaryStage.setScene(scene);
        // primaryStage.initStyle(StageStyle.UNDECORATED);    // 可以隐藏任务栏上的图标
        primaryStage.setOnCloseRequest(windowEvent -> {
            Platform.setImplicitExit(false);
            windowEvent.consume();
            primaryStage.setIconified(true);
            primaryStage.hide();
            System.out.println("禁止关闭");
        });
        /*todo 通过调用 http://localhost:19902/api/db/show */
        // primaryStage.show();

        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get("my.ini")));
        WebService.getIns().setPort(Integer.parseInt(properties.getProperty("web-port")));
        DBFactory.getIns().init(
                properties.getProperty("database"),
                Integer.parseInt(properties.getProperty("port")),
                properties.getProperty("user"),
                properties.getProperty("pwd")
        );
        DBFactory.getIns().print();
        WebService.getIns().start();

        WebService.getIns().initShow();

    }

}