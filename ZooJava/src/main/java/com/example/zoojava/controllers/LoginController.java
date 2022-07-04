package com.example.zoojava.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controlador del login
 */
public class LoginController {
    @FXML
    ImageView imagenLogo;
    @FXML
    TextField emailText;
    @FXML
    TextField passwordText;

    public void closeProgram(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Cerrar programa");
        alerta.setHeaderText("Desea cerrar el programa?");
        alerta.setContentText("Si acepta se cerrará el programa");
        var respuesta = alerta.showAndWait();

        if (respuesta.get()== ButtonType.OK){
            Stage stage = (Stage) imagenLogo.getScene().getWindow();
            stage.close();
        }
    }

    public void openAbout(ActionEvent actionEvent) {
    }

    public void login(ActionEvent actionEvent) {
    }
}