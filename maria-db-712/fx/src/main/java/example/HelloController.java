package example;

import db.server.DBFactory;
import db.server.WebService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Slf4j
public class HelloController {

    @FXML private TextArea textAreaGame;
    @FXML private TextField txt_port;
    @FXML private TextField txt_user;
    @FXML private TextField txt_pwd;
    @FXML private TextField txt_webPort;

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
                        @Override public synchronized void println(String x) {
                            Platform.runLater(() -> {
                                /*委托给ui线程*/
                                try {
                                    if (textAreaGame.getText().length() > 1024 * 1024 * 30) {
                                        String collect = textAreaGame.getText().lines().skip(100).collect(Collectors.joining(System.lineSeparator()));
                                        textAreaGame.setText(collect);
                                    }
                                    textAreaGame.appendText(x + System.lineSeparator());
                                } catch (Exception ignore) {}
                            });
                        }
                    };
                    System.setOut(printStream);
                }
                WebService.getIns().start(Integer.parseInt(txt_webPort.getText()));
                onReStartDb();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void onReStartDb() throws Exception {
        onStopDb();
        DBFactory.getIns().init(
                "local_712_xw",
                Integer.parseInt(txt_port.getText()),
                txt_user.getText(),
                txt_pwd.getText()
        );
        DBFactory.getIns().print();
    }

    public void onStopDb() throws Exception {
        DBFactory.getIns().stop();
    }

    public void onCloseWindow() {
        /*增加30秒强制退出*/
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(30_000);
                Runtime.getRuntime().halt(0);
            } catch (Exception ignore) {}
        });
        hook.start();
    }

}