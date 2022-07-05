package com.example.zoojava.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;


/**
 * Controlador de la escena de acerca de.
 */
public class AboutController {
    @FXML
    private ImageView imagen;

    private final Desktop desktop = Desktop.getDesktop();

    /**
     * Cerrar la escena del acerca de.
     */
    public void closeAbout() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Cerrar About");
        alerta.setHeaderText("Desea cerrar el acerca de?");
        alerta.setContentText("Si acepta se cerrará el acerca de");
        var respuesta = alerta.showAndWait();

        if (respuesta.get()== ButtonType.OK){
            Stage stage = (Stage) imagen.getScene().getWindow();
            stage.close();
        }
    }


    /**
     * Abrir el perfil de github.
     */
    public void openGitHub() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Abriendo página externa");
        confirm.setHeaderText("Desea abrir esta página externa");
        confirm.setContentText("Si confirma se le abrirá la página web seleccionada");
        var isOkay = confirm.showAndWait();
        if (isOkay.get()== ButtonType.OK){
            try {
                desktop.browse(URI.create("https://github.com/Rochiio"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
