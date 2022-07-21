package com.example.zoojava.controllers;

import com.example.zoojava.ZooApplication;
import com.example.zoojava.models.Animal;
import com.example.zoojava.models.enums.typeAnimal;
import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;




/**
 * Controlador de la escena de animales.
 */
public class AnimalsController {
    private AnimalsRepository animals= AnimalsRepositoryImpl.getInstance();
    private final ObservableList<typeAnimal> types = FXCollections.observableArrayList();

    @FXML
    private TableView<Animal> tabla;
    @FXML
    private TableColumn<Animal,String> columnName;
    @FXML
    private TableColumn<Animal, LocalDate> columnBirthDate;
    @FXML
    private ImageView imageAnimal;
    @FXML
    private TextField txtName;
    @FXML
    private DatePicker calendar;
    @FXML
    private ChoiceBox<typeAnimal> choiceType;



    @FXML
    void initialize(){
        types.addAll(typeAnimal.values());
        loadData();
        valoresCeldas();
        eventos();
        cleanAnimal();
        tabla.getSelectionModel().selectFirst();
    }


    /**
     * Añadir los eventos que hay en la tabla.
     */
    private void eventos() {
        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->changeAnimal(newValue)
        );
    }


    /**
     * Cambiar el animal al seleccionar.
     * @param newValue el nuevo valor a poner.
     */
    private void changeAnimal(Animal newValue) {
        if (newValue==null){
            cleanAnimal();
        }else {
            putDataAnimal(newValue);
        }
    }


    /**
     * Poner los nuevos datos.
     * @param newValue los nuevos datos del animal.
     */
    private void putDataAnimal(Animal newValue) {
        txtName.setText(newValue.getName());
        calendar.setValue(newValue.getBirthDate());
        choiceType.setValue(newValue.getType());
        imageAnimal.setImage(changeImage(newValue));
    }


    /**
     * Cambiar la imagen al poner los nuevos valores del animal.
     * @param newValue los nuevos valores a poner.
     * @return la imagen del animal o la imagen por defecto.
     */
    private Image changeImage(Animal newValue) {
        if(newValue.getImg() == null){
            return addImageDefault();
        }else{
            URL imgeUrl = ZooApplication.class.getResource(newValue.getImg());
            return  new Image(String.valueOf(imgeUrl),60,40,false,false);
        }
    }


    /**
     * Poner los valores a las celdas de las tablas.
     */
    private void valoresCeldas() {
        columnName.setCellValueFactory(cellFactory -> cellFactory.getValue().nameProperty());
        columnBirthDate.setCellValueFactory(cellFactory -> cellFactory.getValue().birthDateProperty());
        choiceType.setItems(types);
    }

    /**
     * Cargar los datos de la tabla.
     */
    private void loadData() {
        tabla.setItems(animals.findAll());
    }


    /**
     * Añadir un animal
     * TODO añadir la función de elegir la imagen
     */
    public void addAnimal() {
        if(!isEmptyFields () && isCorrectFields() ) {
            var newAnimal = new Animal(0,txtName.getText(),choiceType.getValue(),calendar.getValue(),null);
            try {
                animals.add(newAnimal);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Ver si los datos son correctos y pasan el filtrado antes de añadir al animal.
     * @return true o false dependiendo de si h a pasado el filtrado.
     */
    private boolean isCorrectFields() {
        StringBuilder sb = new StringBuilder();
        if(!txtName.getText().matches("[A-Z][a-z]*")){
            sb.append("Nombre incorrecto").append("\n");
        }
        if (calendar.getValue().isAfter(LocalDate.now())){
            sb.append("No puede ser la fecha de nacimiento mayor que el día de hoy.").append("\n");
        }

        if (sb.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error de datos");
            alert.setTitle("Error");
            alert.setContentText(sb.toString());
            alert.show();
            return false;
        }

        return true;
    }


    /**
     * Para saber si el TextField está vacío.
     * @return true o false dependiendo de si está vacío.
     */
    private boolean isEmptyFields() {
        return txtName.getText().equals("");
    }


    /**
     * Eliminar al animal seleccionado.
     */
    public void deleteAnimal() {
        var deleteAnimal = tabla.getSelectionModel().getSelectedItem();
        var alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText("Desea eliminar al animal" + deleteAnimal.getName());
        var respuesta = alerta.showAndWait();

        if(respuesta.get()==ButtonType.OK){
            try {
                animals.remove(deleteAnimal);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void modifyAnimal() {
    }


    /**
     * Limpiar los datos sobre el animal.
     */
    public void cleanAnimal() {
        txtName.setText("");
        calendar.setValue(LocalDate.now());
        choiceType.setValue(typeAnimal.MAMIFEROS);
        imageAnimal.setImage(addImageDefault());
    }


    /**
     * Poner la imagen por defecto
     * @return la imagen
     */
    private Image addImageDefault() {
        URL imgeUrl = ZooApplication.class.getResource("icons/img.png");
        return  new Image(String.valueOf(imgeUrl),60,40,false,false);
    }
}
