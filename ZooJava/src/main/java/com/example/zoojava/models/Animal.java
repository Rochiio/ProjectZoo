package com.example.zoojava.models;

import com.example.zoojava.models.enums.typeAnimal;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

/**
 * Clase de creación de los animales del zoo.
 */
public class Animal {
    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<typeAnimal> type;
    private ObjectProperty<LocalDate> birthDate;


    public Animal(IntegerProperty id, StringProperty name, ObjectProperty<typeAnimal> type, ObjectProperty<LocalDate> birthDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public typeAnimal getType() {
        return type.get();
    }

    public ObjectProperty<typeAnimal> typeProperty() {
        return type;
    }

    public void setType(typeAnimal type) {
        this.type.set(type);
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }
}
