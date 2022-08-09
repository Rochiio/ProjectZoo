package com.example.zoojava.controllers;

import com.example.zoojava.ZooApplication;
import com.example.zoojava.managers.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

/**
 * Controlador para la vista inicio-view.
 * Para la elección de ver a los animales o empleados del zoológico.
 */
public class InicioController {
    @FXML
    Button btnEmployee;
    @FXML
    Button btnAnimals;

    @FXML
    void initialize() {
        URL linkEmployee = ZooApplication.class.getResource("icons/empleados.png");
        URL linkAnimals = ZooApplication.class.getResource("icons/animales.png");
        Image employees = new Image(linkEmployee.toString(),60,60,false,false);
        Image animals = new Image(linkAnimals.toString(),60,60,false,false);
        btnAnimals.setGraphic(new ImageView(animals));
        btnEmployee.setGraphic(new ImageView(employees));
    }


    /**
     * Cambiar a la escena de animales.
     */
    public void changeToAnimals( ) {
        SceneManager.animalsScene();
    }


    /**
     * Cambiar a la escena de empleados.
     */
    public void changeToEmployees( ) {
        SceneManager.employeesScene();
    }
}
