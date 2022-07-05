package com.example.zoojava.repositories.employees;

import com.example.zoojava.managers.DataBaseManager;
import com.example.zoojava.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

/**
 * Repositorio de empleados del programa, implementado con una base de datos sqlite y con lista de observables.
 */
public class EmployeesRepositoryImpl implements EmployeesRepository {
    private ObservableList<Employee> list = FXCollections.observableArrayList();
    //TODO añadir constructores para hacer DI.
    private final DataBaseManager db = DataBaseManager.getInstance();


    //TODO cambiar, crear nueva clase de excepcion de empleados
    @Override
    public Employee add(Employee value) throws SQLException {
        String query = "INSERT INTO empleados VALUES (?,?,?,?, ?)";
        db.open();
        var result = db.insert(query,value.getName(),value.getEmail(),value.getPassword(),
                value.getBirthDate().toString(),value.isIsAdmin()).orElseThrow(() -> new RuntimeException("mal"));
        if (result.next()){
            return value;
        }
        return null;
    }

    @Override
    public Employee remove(Employee value) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void removeAll() {

    }

    @Override
    public Employee findByEmail(String email) {
        return null;
    }
}
