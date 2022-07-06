package com.example.zoojava.repositories.animals;

import com.example.zoojava.managers.DataBaseManager;
import com.example.zoojava.models.Animal;
import com.example.zoojava.models.Employee;
import com.example.zoojava.models.enums.typeAnimal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
/**
 * Repositorio de animales del programa, implementado con una base de datos sqlite y con lista de observables.
 * En algunos métodos uso directamente la lista para ahorranos entradas a la base de datos.
 */
public class AnimalsRepositoryImpl implements AnimalsRepository{

    private ObservableList<Animal> list = FXCollections.observableArrayList();
    //TODO añadir constructores para hacer DI.
    private final DataBaseManager db = DataBaseManager.getInstance();


    @Override
    public Animal add(Animal value) throws SQLException {
        String query = "INSERT INTO animales VALUES (null, ?, ?, ?)";
            db.open();
                var result = db.insert(query,value.getName(),value.getType().toString()
                                ,value.getBirthDate().toString());
                if (result.isPresent()) {
                    if (result.get().next()) {
                        value.setId(result.get().getInt("id"));
                        list.add(value);
                        return value;
                    }
                }
            db.close();
        return null;
    }

    @Override
    public Animal remove(Animal value) throws SQLException {
        return null;
    }

    @Override
    public List<Animal> findAll() {
        return null;
    }

    @Override
    public void removeAll() throws SQLException {

    }

    @Override
    public Animal findById(int id) {
        return null;
    }

    @Override
    public Animal modifyById(int id, Animal newAnimal) {
        return null;
    }
}
