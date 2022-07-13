package com.example.zoojava.repositories.animals;

import com.example.zoojava.models.Animal;
import com.example.zoojava.repositories.ICRUD;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface AnimalsRepository extends ICRUD<Animal> {
    ObservableList<Animal> findAll();
    Animal findById(int id);
    Animal modifyById(int id, Animal newAnimal) throws SQLException;
}
