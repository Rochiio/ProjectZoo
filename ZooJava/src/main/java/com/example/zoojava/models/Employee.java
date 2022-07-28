package com.example.zoojava.models;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Clase de creación de los empleados del zoo.
 */
public class Employee {
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;
    private ObjectProperty<LocalDate> birthDate;
    private BooleanProperty isAdmin;

    public Employee(String name, String email, String password, LocalDate birthDate, boolean isAdmin) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.birthDate = new SimpleObjectProperty<>(birthDate);
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
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

    public boolean isIsAdmin() {
        return isAdmin.get();
    }

    public BooleanProperty isAdminProperty() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin.set(isAdmin);
    }

    public String toCsv() {
        return  getName()+";"+getEmail()+";"+getPassword()+";"+getBirthDate().toString()+";"+isIsAdmin();
    }
}
