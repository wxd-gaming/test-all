package mydb;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.PrintStream;

public class HelloController {
    @FXML private TextArea textAreaGame;
    @FXML private TextArea textAreaDb;

    public HelloController() {
        PrintStream printStream = new PrintStream(System.out) {
            @Override public void println(String x) {
                textAreaGame.appendText(x + "\n");
            }
        };
        System.setOut(printStream);
    }
}