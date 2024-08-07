package db712.winfm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Class<HelloApplication> helloApplicationClass = HelloApplication.class;
        URL resource = helloApplicationClass.getResource("hello-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("qj-712-fx-db");
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            System.out.println("禁止关闭");
            stage.setIconified(true);
            windowEvent.consume();
        });
        stage.show();
    }

}