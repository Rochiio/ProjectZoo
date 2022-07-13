package com.example.zoojava.repositories.animals;

import com.example.zoojava.managers.DataBaseManager;
import com.example.zoojava.models.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Repositorio de animales del programa, implementado con una base de datos sqlite y con lista de observables.
 * En algunos métodos uso directamente la lista para ahorranos entradas a la base de datos.
 */
public class AnimalsRepositoryImpl implements AnimalsRepository{
    private static AnimalsRepositoryImpl instance = null;
    private ObservableList<Animal> list = FXCollections.observableArrayList();
    private final DataBaseManager db = DataBaseManager.getInstance();


    public static AnimalsRepositoryImpl getInstance() {
        if (instance == null){
            instance = new AnimalsRepositoryImpl();
        }
        return instance;
    }


    private AnimalsRepositoryImpl() {
    }

    @Override
    public Animal add(Animal value) throws SQLException {
        String query = "INSERT INTO animales VALUES (null, ?, ?, ?, ?)";
            db.open();
                ResultSet result = db.insert(query,value.getName(),value.getType().toString()
                                ,value.getBirthDate().toString(),value.getImg()).orElseThrow(() -> new SQLException("Error al insertar animal"));
                    if (result.next()) {
                        value.setId(result.getInt(1));
                        list.add(value);
                        db.close();
                        return value;
                    }
        return null;
    }

    @Override
    public Animal remove(Animal value) throws SQLException {
        String query = "DELETE FROM animales WHERE id=?";
            db.open();
                var result = db.delete(query,value.getId());
            db.close();
                if (result>0){
                    list.remove(value);
                    return value;
                }
            return null;
    }

    @Override
    public ObservableList<Animal> findAll() {
        return list;
    }

    @Override
    public void removeAll() throws SQLException {
        String query="DELETE FROM animales";
        db.open();
        db.delete(query);
        list.removeAll();
        db.close();
    }

    @Override
    public Animal findById(int id) {
        var find = list.stream().filter(a -> a.getId()==id).findFirst();
        return find.orElse(null);
    }

    @Override
    public Animal modifyById(int id, Animal newAnimal) throws SQLException {
        String query = "UPDATE animales SET name=?, type=?, birthDate=?, img=? WHERE id=?";
            db.open();
                var result = db.update(query,newAnimal.getName(),newAnimal.getType(),newAnimal.getBirthDate(),
                        newAnimal.getImg(),id);
                db.close();
                if (result>0){
                    return newAnimal;
                }
                return null;
    }


}
