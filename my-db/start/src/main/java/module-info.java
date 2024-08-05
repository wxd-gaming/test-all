module wxdgaming.boot.spring.mydb {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    exports mydb;
    opens mydb to javafx.fxml;
}