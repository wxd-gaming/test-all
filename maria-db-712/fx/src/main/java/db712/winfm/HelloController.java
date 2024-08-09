package db712.winfm;

import db712.server.DBFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;


@Slf4j
public class HelloController {

    @FXML private ListView<String> list_view;

    Thread hook;

    public HelloController() throws Exception {

        hook = new Thread(() -> {
            DBFactory.getIns().stop();
            Runtime.getRuntime().halt(0);
        });

        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
                {
                    /*TODO 必须要等他初始化完成*/
                    PrintStream printStream = new PrintStream(System.out) {
                        @Override public void print(String x) {
                            Platform.runLater(() -> {
                                /*委托给ui线程*/
                                try {
                                    while (list_view.getItems().size() > 1000) {
                                        list_view.getItems().removeFirst();
                                    }
                                    list_view.getItems().add(x);
                                    list_view.scrollTo(list_view.getItems().size() + 999);
                                } catch (Throwable ignore) {}
                            });
                        }
                    };
                    System.setOut(printStream);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

}