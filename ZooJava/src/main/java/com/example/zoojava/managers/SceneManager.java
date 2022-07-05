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

    /**
     * Escena del login
     * @param stage stage al que añadir la escena
     */
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
        stage.setResizable(false);
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


    /**
     * Abrir escena del acerca de.
     */
    public static void openAbout() {
        FXMLLoader fxmlLoader = new FXMLLoader(ZooApplication.class.getResource("views/about-view.fxml"));
        Scene scene;
        Stage stage = new Stage();
        try {
            scene = new Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH_ABOUT, PropertiesNumbers.HEIGHT_ABOUT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("About");
        stage.setScene(scene);
        stage.setResizable(false);
        addIcon(stage);
        stage.show();
    }
}
