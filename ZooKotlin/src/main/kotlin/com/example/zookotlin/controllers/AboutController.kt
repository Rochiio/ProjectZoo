package com.example.zookotlin.controllers

import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.image.ImageView
import javafx.stage.Stage
import java.awt.Desktop
import java.net.URI

/**
 * Controlador escena acerca de.
 */
class AboutController {
    @FXML
    private lateinit var imagen: ImageView

    private val desktop = Desktop.getDesktop()


    /**
     * Cerrar la escena del acerca de.
     */
    fun closeAbout(){
        var alerta = Alert(Alert.AlertType.CONFIRMATION)
        alerta.title="Cerrar About"
        alerta.headerText="Desea cerrar el acerca de?"
        alerta.contentText="Si acepta se cerrará el acerca de"
        var respuesta = alerta.showAndWait()

        if (respuesta.get() == ButtonType.OK){
            val stage = imagen.scene.window as Stage
            stage.close()
        }
    }


    /**
     * Abrir el perfil de github
     */
    fun openGitHub(){
        var alerta = Alert(Alert.AlertType.CONFIRMATION)
        alerta.title="Abriendo página externa"
        alerta.headerText="Desea abirir esta página externa"
        alerta.contentText="Si confirma se le abrirá la página web seleccionada"
        var respuesta = alerta.showAndWait()

        if (respuesta.get() == ButtonType.OK){
            desktop.browse(URI.create("https://github.com/Rochiio"))
        }
    }
}