package example;

import db.server.DBFactory;
import db.server.WebService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;
import java.util.concurrent.CompletableFuture;


@Slf4j
public class HelloController {


    @FXML private TextArea textAreaGame;
    @FXML private TextField txt_port;
    @FXML private TextField txt_user;
    @FXML private TextField txt_pwd;

    Thread hook;

    public HelloController() throws Exception {
        PrintStream printStream = new PrintStream(System.out) {
            @Override public void println(String x) {
                if (textAreaGame.getText().length() > 1024 * 1024 * 30) {
                    int i = textAreaGame.getText().indexOf("\n");
                    textAreaGame.setText(textAreaGame.getText().substring(i + 1));
                }
                textAreaGame.appendText(x + "\n");
            }
        };
        System.setOut(printStream);

        hook = new Thread(() -> {
            DBFactory.getIns().stop();
            Runtime.getRuntime().halt(0);
        });
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
                WebService.getIns().start();
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
        log.info("启动成功");
    }

    public void onStopDb() throws Exception {
        DBFactory.getIns().stop();
    }

    public void onCloseWindow() {
        hook.start();
    }

}