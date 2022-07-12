package com.example.zoojava.controllers;

import com.example.zoojava.ZooApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class InicioController {
    @FXML
    Button btnEmployee;
    @FXML
    Button btnAnimals;

    @FXML
    void initialize() {
        URL linkEmployee = ZooApplication.class.getResource("icons/empleados.png");
        URL linkAnimals = ZooApplication.class.getResource("icons/animales.png");
        Image employees = new Image(linkEmployee.toString(),24,24,false,false);
        Image animals = new Image(linkAnimals.toString(),24,24,false,false);
        btnAnimals.setGraphic(new ImageView(animals));
        btnEmployee.setGraphic(new ImageView(employees));
    }


    public void chaneToEmployees(ActionEvent actionEvent) {
    }

    public void changeToAnimals(ActionEvent actionEvent) {
    }
}
