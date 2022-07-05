module com.example.zoojava {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;


    opens com.example.zoojava to javafx.fxml;
    opens com.example.zoojava.views;
    exports com.example.zoojava;
    exports com.example.zoojava.controllers;
    opens com.example.zoojava.controllers to javafx.fxml;
}