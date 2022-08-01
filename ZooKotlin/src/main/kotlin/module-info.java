module com.example.zookotlin {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.zookotlin to javafx.fxml;
    exports com.example.zookotlin;
}