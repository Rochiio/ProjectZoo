package com.example.zookotlin.controllers

import com.example.zookotlin.models.Employee
import com.example.zookotlin.repositories.employees.EmployeesRepository
import com.example.zookotlin.repositories.employees.EmployeesRepositoryImpl
import com.example.zookotlin.utils.Globals
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.fxml.FXML
import javafx.scene.control.*
import java.sql.SQLException
import java.time.LocalDate

/**
 * Controlador para la escena de los empleados.
 */
class EmployeesController {
    private val employees: EmployeesRepository = EmployeesRepositoryImpl.getInstance()!!

    @FXML
    private lateinit var tabla: TableView<Employee>

    @FXML
    private lateinit var columnName: TableColumn<Employee, String>

    @FXML
    private lateinit var columnEmail: TableColumn<Employee, String>

    @FXML
    private lateinit var txtName: TextField

    @FXML
    private lateinit var txtEmail: TextField

    @FXML
    private lateinit var txtPass: PasswordField

    @FXML
    private lateinit var txtConfirmPass: PasswordField

    @FXML
    private lateinit var calendar: DatePicker

    @FXML
    private lateinit var checkAdmin: CheckBox

    @FXML
    private lateinit var btnDelete: Button

    @FXML
    private lateinit var btnAdd: Button

    @FXML
    private lateinit var btnClean: Button


    @FXML
    fun initialize() {
        opcionesAdmin()
        loadData()
        valoresCeldas()
        eventos()
        cleanEmployee()
        tabla.selectionModel.selectFirst()
    }


    /**
     * Opciones que solo tienen habilitados los administradores.
     * Los usuarios que no son administradores podrán ver los empleados que hay en el sistema pero no podrán realizar
     * ninguna acción con ellos.
     */
    private fun opcionesAdmin() {
        val employee = Globals.globalEmployee
        if (!employee.isIsAdmin()) {
            btnDelete.isDisable = true
            btnAdd.isDisable = true
            btnClean.isDisable = true
        }
    }


    /**
     * Eventos en la tabla
     */
    private fun eventos() {
        tabla.selectionModel.selectedItemProperty().addListener{ observable, oldValue, newValue ->
                cambiarValor(
                    newValue
                )
            }
    }


    /**
     * Cambiar el valor de empleado.
     * @param newValue nuevos valores a mostrar.
     */
    private fun cambiarValor(newValue: Employee?) {
        if (newValue == null) {
            cleanEmployee()
        } else {
            nuevosValores(newValue)
        }
    }


    /**
     * Los nuevos valores a mostrar.
     * @param newValue valores seleccionados.
     */
    private fun nuevosValores(newValue: Employee) {
        txtName.text = newValue.getName()
        txtEmail.text = newValue.getEmail()
        txtPass.text = newValue.getPassword()
        txtConfirmPass.text = ""
        calendar.value = newValue.getBirthDate()
        if (newValue.isIsAdmin()) {
            checkAdmin.isSelected = true
        }
    }


    /**
     * Valores de las celdas de la tabla.
     */
    private fun valoresCeldas() {
        columnName.setCellValueFactory { cellValue: TableColumn.CellDataFeatures<Employee, String?> ->
            cellValue.value.nameProperty()
        }
        columnEmail.setCellValueFactory { cellValue: TableColumn.CellDataFeatures<Employee, String?> ->
            cellValue.value.emailProperty()
        }
    }


    /**
     * Cargamos los datos en la tabla.
     */
    private fun loadData() {
        tabla.items=employees.findAll()
    }


    /**
     * Añadir un empleado.
     */
    fun addEmployee() {
        if (isCorrectFields()) {
            if (isEmailValid()) {
                try {
                    employees.add(
                        Employee(
                            txtName.text, txtEmail.text, txtPass.text, calendar.value, checkAdmin.isSelected
                        )
                    )
                } catch (e: SQLException) {
                    throw RuntimeException(e)
                }
            }
        }
    }


    /**
     * Para saber si ya existe un empleado con dicho correo
     * @return verdadero o falso dependiendo de si existe un empleado con ese email
     */
    private fun isEmailValid(): Boolean {
        val employee= employees.findByEmail(txtEmail.text)
        if (employee != null) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error"
            alert.headerText = "Error con el correo electrónico"
            alert.contentText = "Ya existe una cuenta con este mismo correo electrónico"
            alert.show()
            return false
        }
        return true
    }


    /**
     * Filtrado de datos.
     * @return true o false dependiendo de si ha pasado el filtrado.
     */
    private fun isCorrectFields(): Boolean {
        val sb = StringBuilder()
        if (txtName.text == "" || !txtName.text.matches(Regex("[A-Z][a-z]*"))) {
            sb.append("El nombre no puede estar vacío o ser incorrecto").append("\n")
        }
        if (txtEmail.text == "" || !txtEmail.text.matches(Regex("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$"))) {
            sb.append("El correo no puede estar vacío o ser incorrecto").append("\n")
        }
        if (txtPass.text == "" || txtPass.text.length < 7) {
            sb.append("La contraseña no puede estar vacía o tener una longuitud menor o igual a 7").append("\n")
        }
        if (txtConfirmPass.text == "" || txtConfirmPass.text != txtPass.text) {
            sb.append("La confirmación de contraseña no puede estar vacía o ser distinta de la contraseña").append("\n")
        }
        if (calendar.value.isAfter(LocalDate.now())) {
            sb.append("La fecha de nacimiento no puede ser mayor a hoy").append("\n")
        }
        if (sb.isNotEmpty()) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Error"
            alert.headerText = "Error en la validación de datos"
            alert.contentText = sb.toString()
            alert.show()
            return false
        }
        return true
    }


    /**
     * Eliminar un empleado.
     */
    fun deleteEmployee() {
        val selectEmployee: Employee = tabla.selectionModel.selectedItem
        val confirmacion = Alert(Alert.AlertType.CONFIRMATION)
        confirmacion.title = "Confirmación"
        confirmacion.headerText = "Confirmación de eliminación de empleado"
        confirmacion.contentText = "Desea eliminar al empleado " + selectEmployee.getName() + " ?"
        val result = confirmacion.showAndWait()
        if (result.get() == ButtonType.OK) {
            employees.remove(selectEmployee)
        }
    }


    /**
     * Limpiar datos.
     */
    fun cleanEmployee() {
        txtName.text = ""
        txtEmail.text = ""
        txtPass.text = ""
        txtConfirmPass.text = ""
        calendar.value = LocalDate.now()
        checkAdmin.isSelected = false
    }
}