package com.example.zoojava.managers;

import com.example.zoojava.ZooApplication;
import com.example.zoojava.utils.PropertiesNumbers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase para manejar las diferentes escenas del programa.
 */
public class SceneManager {

    public static void loginScene(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("views/login-view.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH, PropertiesNumbers.HEIGHT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Login");
        stage.setScene(scene);
        addIcon(stage);
        stage.show();
    }


    /**
     * Para añadir a las escenas el icono.
     * @param stage stage a añadir el icono.
     */
    private static void addIcon(Stage stage){
        stage.getIcons().add(new Image(String.valueOf(ZooApplication.class.getResource("icons/zoo.png"))));
    }
}
