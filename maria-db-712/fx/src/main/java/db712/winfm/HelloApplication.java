package db712.winfm;

import com.sun.javafx.application.PlatformImpl;
import db712.server.DBFactory;
import db712.server.WebService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image image_logo = new Image("logo.png");
        /*阻止停止运行*/
        Platform.setImplicitExit(false);
        WebService.getIns().setShowWindow(() -> {
            PlatformImpl.runAndWait(() -> {
                /*todo 必须要交给ui线程*/
                primaryStage.setIconified(false);
                primaryStage.show();
                /*设置最顶层*/
                primaryStage.setAlwaysOnTop(true);
                primaryStage.setAlwaysOnTop(false);
            });
        });
        Class<HelloApplication> helloApplicationClass = HelloApplication.class;
        URL resource = helloApplicationClass.getResource("hello-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);

        Scene scene = new Scene(fxmlLoader.load(), Color.BLACK);
        primaryStage.setTitle("qj-712-db-server");
        primaryStage.setScene(scene);

        primaryStage.getIcons().add(image_logo);
        // primaryStage.initStyle(StageStyle.UNDECORATED);    // 可以隐藏任务栏上的图标
        primaryStage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            select(primaryStage);
        });
        /*todo 通过调用 http://localhost:19902/api/db/show */
        primaryStage.show();
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setAlwaysOnTop(false);
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
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

                CompletableFuture.runAsync(() -> {

                    try {
                        Thread.sleep(10_000);
                        PlatformImpl.runAndWait(() -> {
                            primaryStage.hide();
                        });
                    } catch (Exception ignore) {}

                });
            } catch (Throwable e) {
                e.printStackTrace(System.out);
                System.out.println("启动异常了！");
                System.out.println("启动异常了！");
                System.out.println("启动异常了！");
                System.out.println("启动异常了！");
            }
        });
    }

    public void select(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("提示");
        alert.setHeaderText("点击按钮以确认你的选择!");
        // alert.setContentText("点击按钮以确认你的选择。");

        ButtonType buttonTypeOk = new ButtonType("关闭进程", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("后台运行", ButtonBar.ButtonData.OK_DONE);

        alert.getButtonTypes().setAll(buttonTypeCancel, buttonTypeOk);

        ButtonType result = alert.showAndWait().orElse(buttonTypeCancel);

        if (result == buttonTypeOk) {
            Runtime.getRuntime().exit(0);
        } else {
            primaryStage.hide();
        }
    }
}