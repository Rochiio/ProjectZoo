package com.example.zookotlin.controllers

import com.example.zookotlin.ZooApplication
import com.example.zookotlin.models.Animal
import com.example.zookotlin.models.enums.typeAnimal
import com.example.zookotlin.repositories.animals.AnimalsRepository
import com.example.zookotlin.repositories.animals.AnimalsRepositoryImpl
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.FileChooser
import javafx.util.Callback
import java.net.URL
import java.sql.SQLException
import java.time.LocalDate

/**
 * Controlador de la escena de animales.
 */
class AnimalsController {
    private val animals: AnimalsRepository = AnimalsRepositoryImpl.getInstance()!!
    private val types: ObservableList<typeAnimal> = FXCollections.observableArrayList()

    @FXML
    private lateinit var tabla: TableView<Animal>

    @FXML
    private lateinit var columnName: TableColumn<Animal, String>

    @FXML
    private lateinit var columnBirthDate: TableColumn<Animal, LocalDate>

    @FXML
    private lateinit var imageAnimal: ImageView

    @FXML
    private lateinit var txtId: TextField

    @FXML
    private lateinit var txtName: TextField

    @FXML
    private lateinit var calendar: DatePicker

    @FXML
    private lateinit var choiceType: ChoiceBox<typeAnimal>


    @FXML
    fun initialize() {
        types.addAll(typeAnimal.values())
        txtId.isDisable = true
        loadData()
        valoresCeldas()
        eventos()
        cleanAnimal()
        tabla.selectionModel.selectFirst()
    }


    /**
     * Añadir los eventos que hay en la tabla.
     */
    private fun eventos() {
        tabla.selectionModel.selectedItemProperty()
            .addListener { observable, oldValue, newValue ->
                changeAnimal(
                    newValue
                )
            }
    }


    /**
     * Cambiar el animal al seleccionar.
     * @param newValue el nuevo valor a poner.
     */
    private fun changeAnimal(newValue: Animal?) {
        if (newValue == null) {
            cleanAnimal()
        } else {
            putDataAnimal(newValue)
        }
    }


    /**
     * Poner los nuevos datos.
     * @param newValue los nuevos datos del animal.
     */
    private fun putDataAnimal(newValue: Animal) {
        txtId.text = java.lang.String.valueOf(newValue.getId())
        txtName.text = newValue.getName()
        calendar.value = newValue.getBirthDate()
        choiceType.value = newValue.getType()
        imageAnimal.image = changeImage(newValue)
    }


    /**
     * Cambiar la imagen al poner los nuevos valores del animal.
     * @param newValue los nuevos valores a poner.
     * @return la imagen del animal o la imagen por defecto.
     */
    private fun changeImage(newValue: Animal): Image {
        return if (newValue.getImg() == null || newValue.getImg() == "null") {
            addImageDefault()
        } else {
            Image(java.lang.String.valueOf(newValue.getImg()))
        }
    }


    /**
     * Poner los valores a las celdas de las tablas.
     */
    private fun valoresCeldas() {
       columnName.setCellValueFactory { cellFactory -> cellFactory.value.nameProperty() }
       columnBirthDate.setCellValueFactory { cellFactory -> cellFactory.value.birthDateProperty()}
       choiceType.items = types
    }


    /**
     * Cargar los datos de la tabla.
     */
    private fun loadData() {
        tabla.items = animals.findAll()
    }


    /**
     * Añadir un animal
     */
    fun addAnimal() {
        if (!isEmptyFields() && isCorrectFields()) {
            val newAnimal = Animal(0, txtName.text, choiceType.value, calendar.value, imageAnimal.image.toString())
            try {
                animals.add(newAnimal)
            } catch (e: SQLException) {
                throw RuntimeException(e)
            }
        }
    }


    /**
     * Ver si los datos son correctos y pasan el filtrado antes de añadir al animal.
     * @return true o false dependiendo de si h a pasado el filtrado.
     */
    private fun isCorrectFields(): Boolean {
        val sb = StringBuilder()
        if (!txtName.text.matches(Regex("[A-Z][a-z]*"))) {
            sb.append("Nombre incorrecto").append("\n")
        }
        if (calendar.value.isAfter(LocalDate.now())) {
            sb.append("No puede ser la fecha de nacimiento mayor que el día de hoy.").append("\n")
        }
        if (sb.isNotEmpty()) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.headerText = "Error de datos"
            alert.title = "Error"
            alert.contentText = sb.toString()
            alert.show()
            return false
        }
        return true
    }


    /**
     * Para saber si el TextField está vacío.
     * @return true o false dependiendo de si está vacío.
     */
    private fun isEmptyFields(): Boolean {
        return txtName.text == ""
    }


    /**
     * Eliminar al animal seleccionado.
     */
    fun deleteAnimal() {
        val deleteAnimal = tabla.selectionModel.selectedItem
        val alerta = Alert(Alert.AlertType.CONFIRMATION)
        alerta.title = "Confirmación"
        alerta.headerText = "Desea eliminar al animal" + deleteAnimal.getName()
        val respuesta = alerta.showAndWait()
        if (respuesta.get() == ButtonType.OK) {
            try {
                animals.remove(deleteAnimal)
            } catch (e: SQLException) {
                throw RuntimeException(e)
            }
        }
    }


    /**
     * Modificar a un animal.
     */
    fun modifyAnimal() {
        if (!isEmptyFields() && isCorrectFields()) {
            val animal = tabla.selectionModel.selectedItem
            val modifyAnimal = Animal(animal.getId(), txtName.text, choiceType.value, calendar.value, imageAnimal.image.toString())
            try {
                animals.modifyById(animal.getId(), modifyAnimal)
            } catch (e: SQLException) {
                throw RuntimeException(e)
            }
        }
    }


    /**
     * Limpiar los datos sobre el animal.
     */
    fun cleanAnimal() {
        txtName.text = "0"
        txtName.text = ""
        calendar.value = LocalDate.now()
        choiceType.value = typeAnimal.MAMIFEROS
        imageAnimal.image = addImageDefault()
    }


    /**
     * Poner la imagen por defecto
     * @return la imagen
     */
    private fun addImageDefault(): Image {
        val imgeUrl: URL = ZooApplication::class.java.getResource("icons/img.png") as URL
        return Image(imgeUrl.toString())
    }

    /**
     * Poner la nueva imagen.
     */
    fun newImage() {
        val fileChooser = FileChooser()
        fileChooser.title = "Elegir nueva imagen"
        fileChooser.extensionFilters.addAll(
            FileChooser.ExtensionFilter("imagenes png", "*.png"), FileChooser.ExtensionFilter("imagenes jpg", "*.jpg")
        )
        val img = fileChooser.showOpenDialog(txtName.scene.window)
        if (img != null) {
            imageAnimal.image = Image(img.absolutePath)
            val animal = tabla.selectionModel.selectedItem
            animal.setImg(img.absolutePath)
            try {
                animals.modifyById(animal.getId(), animal)
            } catch (e: SQLException) {
                throw RuntimeException(e)
            }
        }
    }
}