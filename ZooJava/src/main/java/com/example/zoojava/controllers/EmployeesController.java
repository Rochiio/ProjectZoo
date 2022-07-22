package com.example.zoojava.controllers;

import com.example.zoojava.models.Employee;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;


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
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnClean;


    @FXML
    void initialize() {
        opcionesAdmin();
        loadData();
        valoresCeldas();
        eventos();
        cleanEmployee();
        tabla.getSelectionModel().selectFirst();
    }


    /**
     * Opciones que solo tienen habilitados los administradores.
     */
    private void opcionesAdmin() {
    }


    /**
     * Eventos en la tabla
     */
    private void eventos() {
        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue, newValue) -> {cambiarValor(newValue);}
        );
    }


    /**
     * Cambiar el valor de empleado.
     * @param newValue nuevos valores a mostrar.
     */
    private void cambiarValor(Employee newValue) {
        if (newValue==null){
            cleanEmployee();
        }else{
            nuevosValores(newValue);
        }
    }


    /**
     * Los nuevos valores a mostrar.
     * @param newValue valores seleccionados.
     */
    private void nuevosValores(Employee newValue) {
        txtName.setText(newValue.getName());
        txtEmail.setText(newValue.getEmail());
        txtPass.setText(newValue.getPassword());
        txtConfirmPass.setText("");
        calendar.setValue(newValue.getBirthDate());
        if (newValue.isIsAdmin()){
            checkAdmin.isSelected();
        }
    }


    /**
     * Valores de las celdas de la tabla.
     */
    private void valoresCeldas() {
        columnName.setCellValueFactory(cellValue -> cellValue.getValue().nameProperty());
        columnEmail.setCellValueFactory(cellValue -> cellValue.getValue().emailProperty());
    }


    /**
     * Cargamos los datos en la tabla.
     */
    private void loadData() {
        tabla.setItems(employees.findAll());
    }


    /**
     * Añadir un empleado.
     */
    public void addEmployee() {
    }


    /**
     * Eliminar un empleado.
     */
    public void deleteEmployee() {
    }


    /**
     * Modificar un empleado.
     */
    public void modifyEmployee() {
    }


    /**
     * Limpiar datos.
     */
    public void cleanEmployee() {
        txtName.setText("");
        txtEmail.setText("");
        txtPass.setText("");
        txtConfirmPass.setText("");
        calendar.setValue(LocalDate.now());
        checkAdmin.isSelected();
    }
}
