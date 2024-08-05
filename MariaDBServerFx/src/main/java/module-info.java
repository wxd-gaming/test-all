module fxdb.fxdb {
    requires java.management;
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.httpserver;
    requires org.kordamp.bootstrapfx.core;
    requires ch.vorburger.mariadb4j;
    requires ch.vorburger.exec;

    opens fxdb.fxdb to javafx.fxml;
    exports fxdb.fxdb;
}