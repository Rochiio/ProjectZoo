package com.example.zoojava;

import com.example.zoojava.managers.SceneManager;
import com.example.zoojava.utils.DataSystem;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Rocío P.F
 * @version 1.0
 * github: Rochiio
 * Programa para la administración de un zoológico, utilizando dagger, logger y haciendo test.
 */
public class ZooApplication extends Application {
//TODO al final de hacer el programa añadir dagger y logger
    //TODO añadir backup de empleados y animales

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.loginScene(stage);
    }

    public static void main(String[] args) {
        DataSystem system = new DataSystem();
        system.addData();
        launch();
        system.backup();
    }



}