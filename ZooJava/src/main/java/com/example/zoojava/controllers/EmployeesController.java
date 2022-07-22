package com.example.zoojava.controllers;

import com.example.zoojava.models.Employee;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import com.example.zoojava.utils.Globals;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
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
    private Button btnDelete;
    @FXML
    private Button btnModify;


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
        var employee = Globals.globalEmployee;
        if (!employee.isIsAdmin()){
            btnDelete.setDisable(true);
            btnModify.setDisable(true);
        }
    }


    /**
     * Eventos en la tabla
     */
    private void eventos() {
        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable,oldValue, newValue) -> cambiarValor(newValue)
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
            checkAdmin.setSelected(true);
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
        if(isCorrectFields()){
            if (isEmailValid()) {
                try {
                    employees.add(new Employee(txtName.getText(),txtEmail.getText(),txtPass.getText()
                            ,calendar.getValue(),checkAdmin.isSelected()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    /**
     * Para saber si ya existe un empleado con dicho correo
     * @return verdader o falso dependiendo de si existe un empleado con ese email
     */
    private boolean isEmailValid() {
        var employee = employees.findByEmail(txtEmail.getText());
        if (employee != null){
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error con el correo electrónico");
            alert.setContentText("Ya existe una cuenta con este mismo correo electrónico");
            alert.show();
            return false;
        }
      return  true;
    }


    /**
     * Filtrado de datos.
     * @return true o false dependiendo de si ha pasado el filtrado.
     */
    private boolean isCorrectFields() {
        StringBuilder sb = new StringBuilder();
        if (txtName.getText().equals("") || !txtName.getText().matches("[A-Z][a-z]*")){
            sb.append("El nombre no puede estar vacío o ser incorrecto").append("\n");
        }
        if (txtEmail.getText().equals("") || !txtEmail.getText().matches("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$")){
            sb.append("El correo no puede estar vacío o ser incorrecto").append("\n");
        }
        if (txtPass.getText().equals("") || txtPass.getText().length()<7){
            sb.append("La contraseña no puede estar vacía o tener una longuitud menor o igual a 7").append("\n");
        }
        if(txtConfirmPass.getText().equals("") || !txtConfirmPass.getText().equals(txtPass.getText())){
            sb.append("La confirmación de contraseña no puede estar vacía o ser distinta de la contraseña").append("\n");
        }
        if (calendar.getValue().isAfter(LocalDate.now())){
            sb.append("La fecha de nacimiento no puede ser mayor a hoy").append("\n");
        }

            if (sb.length()>0){
                var alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error en la validación de datos");
                alert.setContentText(sb.toString());
                alert.show();
                return false;
            }
        return  true;
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
        checkAdmin.setSelected(false);
    }
}
