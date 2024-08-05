package fxdb.fxdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
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

    public static void main(String[] args) {
        launch();
    }
}