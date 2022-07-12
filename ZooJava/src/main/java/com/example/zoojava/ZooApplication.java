package com.example.zoojava;

import com.example.zoojava.DI.components.DaggerDataSystemComponent;
import com.example.zoojava.managers.SceneManager;
import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import com.example.zoojava.utils.DataSystem;
import com.example.zoojava.utils.csv.ImportAnimalCsv;
import com.example.zoojava.utils.csv.ImportAnimalCsvImpl;
import com.example.zoojava.utils.csv.ImportEmployeeCsv;
import com.example.zoojava.utils.csv.ImportEmployeeCsvImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Rocío P.F
 * @version 1.0
 * github: Rochiio
 * Programa para la administración de un zoológico, utilizando testing, dagger, logger.
 */
public class ZooApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.loginScene(stage);
    }

    public static void main(String[] args) {
        DataSystem system = DaggerDataSystemComponent.create().build();
        system.addData();
        launch();
    }



}