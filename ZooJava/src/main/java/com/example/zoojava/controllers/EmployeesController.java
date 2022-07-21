package com.example.zoojava.controllers;

import com.example.zoojava.models.Employee;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


/**
 * Controlador para la escena de los empleados.
 */
public class EmployeesController {
    private EmployeesRepository employees= EmployeesRepositoryImpl.getInstance();
    @FXML
    private TableView<Employee> tabla;
    @FXML
    private TableColumn<Employee,String> columnName;
    @FXML
    private TableColumn<Employee, String> columnEmail;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPass;
    @FXML
    private PasswordField txtConfirmPass;
    @FXML
    private DatePicker calendar;
    @FXML
    private CheckBox checkAdmin;

    public void addEmployee(ActionEvent actionEvent) {
    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }

    public void modifyEmployee(ActionEvent actionEvent) {
    }

    public void cleanEmployee(ActionEvent actionEvent) {
    }
}
