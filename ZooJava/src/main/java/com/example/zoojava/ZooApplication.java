package com.example.zoojava;

import com.example.zoojava.managers.SceneManager;
import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
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
    private static ImportAnimalCsv animalCsv = new ImportAnimalCsvImpl();
    private static ImportEmployeeCsv employeeCsv = new ImportEmployeeCsvImpl();
    private static AnimalsRepository animalsRepository = new AnimalsRepositoryImpl();
    private static EmployeesRepository employeesRepository = new EmployeesRepositoryImpl();

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.loginScene(stage);
    }

    public static void main(String[] args) {
        putData();
        launch();
    }


    /**
     * Para añadir los datos base sacados del csv
     */
    private static void putData() {
        var listEmployee = employeeCsv.importData();
        var listAnimals = animalCsv.importData();

            listEmployee.forEach((a) -> {
                try {
                    employeesRepository.add(a);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            listAnimals.forEach((a) -> {
                try {
                    animalsRepository.add(a);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

    }


}