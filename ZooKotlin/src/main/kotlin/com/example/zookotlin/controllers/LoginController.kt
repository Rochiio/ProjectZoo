package com.example.zookotlin.controllers

import com.example.zookotlin.managers.SceneManager
import com.example.zookotlin.repositories.employees.EmployeesRepository
import com.example.zookotlin.repositories.employees.EmployeesRepositoryImpl
import com.example.zookotlin.utils.Globals
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.stage.Stage

/**
 * Controlador del login.
 */
class LoginController {
    private val repository: EmployeesRepository = EmployeesRepositoryImpl.getInstance()!!

    @FXML
    private lateinit var imagenLogo: ImageView

    @FXML
    private lateinit var emailText: TextField

    @FXML
    private lateinit var passwordText: TextField


    /**
     * Para cerrar el programa, dependiendo de su confirmación.
     */
    fun closeProgram() {
        val alerta = Alert(Alert.AlertType.CONFIRMATION)
        alerta.title = "Cerrar programa"
        alerta.headerText = "Desea cerrar el programa?"
        alerta.contentText = "Si acepta se cerrará el programa"
        val respuesta = alerta.showAndWait()
        if (respuesta.get() == ButtonType.OK) {
            val stage = imagenLogo.scene.window as Stage
            stage.close()
        }
    }


    /**
     * Para abrir el acerca de, del programa.
     */
    fun openAbout() {
        SceneManager.openAbout()
    }


    /**
     * Login del usuario.
     */
    fun login() {
        if (!isErrorsEmpty()) {
            isCorrectLogin()
        }
    }


    /**
     * Para saber si el usuario es correcto.
     */
    private fun isCorrectLogin() {
        val user = repository.findByEmail(emailText.text)
        if (user != null) {
            if (user.getPassword() == passwordText.text) {
                Globals.globalEmployee = user
                val alerta = Alert(Alert.AlertType.INFORMATION)
                alerta.title = "Login Correcto"
                alerta.headerText = "Bienvenido " + user.getName()
                alerta.show()
                SceneManager.openInicio(imagenLogo.scene.window as Stage)
            } else {
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Error de login"
                alert.contentText = "Login incorrecto"
                alert.show()
            }
        } else {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error de login"
            alert.contentText = "Usuario no encontrado"
            alert.show()
        }
    }


    /**
     * Método para ver si el usuario se ha dejado campos vacíos.
     * @return boolean dependiendo de si ha habido errores o no.
     */
    private fun isErrorsEmpty(): Boolean {
        val sb = StringBuilder()
        if (emailText.text == "" || passwordText.text == "") {
            sb.append("No se pueden dejar campos vacíos").append("\n")
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error de campos"
            alert.contentText = sb.toString()
            alert.show()
            return true
        }
        return false
    }

}