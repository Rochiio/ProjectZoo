package com.example.zoojava.controllers;

import com.example.zoojava.DI.components.DaggerEmployeeRepositoryComponent;
import com.example.zoojava.managers.SceneManager;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.utils.Globals;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;



/**
 * Controlador del login
 */
public class LoginController {
    private EmployeesRepository repository= DaggerEmployeeRepositoryComponent.create().build();
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
    public void login(ActionEvent actionEvent) {
        if (!isErrorsEmpty()){
            isCorrectLogin();
        }
    }


    /**
     * Para saber si el usuario es correcto.
     */
    private void isCorrectLogin() {
        var user = repository.findByEmail(emailText.getText());
            if(user!=null){
                if (user.getPassword().equals(passwordText.getText())){
                    Globals.globalEmployee=user;
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Login Correcto");
                    alerta.setHeaderText("Bienvenido "+ user.getName());
                    alerta.show();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de login");
                    alert.setContentText("Login incorrecto");
                    alert.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de login");
                alert.setContentText("Usuario no encontrado");
                alert.show();
            }
    }


    /**
     * Método para ver si el usuario se ha dejado campos vacíos.
     * @return boolean dependiendo de si ha habido errores o no.
     */
    private boolean isErrorsEmpty() {
        StringBuilder sb = new StringBuilder();
        if (emailText.getText().equals("") || passwordText.getText().equals("")){
            sb.append("No se pueden dejar campos vacíos").append("\n");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de campos");
            alert.setContentText(sb.toString());
            alert.show();
            return true;
        }
       return false;
    }


}