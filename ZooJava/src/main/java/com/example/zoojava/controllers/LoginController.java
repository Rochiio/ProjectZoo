package com.example.zoojava.controllers;

import com.example.zoojava.managers.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controlador del login
 */
public class LoginController {

    @FXML
    private ImageView imagenLogo;
    @FXML
    private TextField emailText;
    @FXML
    private TextField passwordText;


    /**
     * Para cerrar el programa, dependiendo de su confirmación.
     */
    public void closeProgram() {
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


    /**
     * Para abrir el acerca de, del programa.
     */
    public void openAbout() {
        SceneManager.openAbout();
    }


    /**
     * Login del usuario.
     */
    public void login() {
    }
}