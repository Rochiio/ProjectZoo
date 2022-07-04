package com.example.zoojava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Rocío P.F
 * @version 1.0
 * github: Rochiio
 * Programa para la administración de un zoológico, utilizando testing, dagger, logger.
 */
public class ZooApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(ZooApplication.class.getResource("icons/zoo.png"))));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}