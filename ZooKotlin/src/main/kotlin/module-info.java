module com.example.zookotlin {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.zookotlin to javafx.fxml;
    opens com.example.zookotlin.controllers to javafx.fxml;
    exports com.example.zookotlin;
    exports com.example.zookotlin.controllers;
}