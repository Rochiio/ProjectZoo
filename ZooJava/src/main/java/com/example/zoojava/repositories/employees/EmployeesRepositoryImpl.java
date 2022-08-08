package com.example.zoojava.repositories.employees;


import com.example.zoojava.managers.DataBaseManager;
import com.example.zoojava.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.SQLException;

/**
 * Repositorio de empleados del programa, implementado con una base de datos sqlite y con lista de observables.
 * En algunos métodos uso directamente la lista para ahorranos entradas a la base de datos.
 */
public class EmployeesRepositoryImpl implements EmployeesRepository {
    private static EmployeesRepositoryImpl instance = null;
    private ObservableList<Employee> list = FXCollections.observableArrayList();

    private final DataBaseManager db = DataBaseManager.getInstance();


    public static EmployeesRepositoryImpl getInstance() {
        if (instance == null){
            instance = new EmployeesRepositoryImpl();
        }
        return instance;
    }

    private EmployeesRepositoryImpl() {
    }

    @Override
    public Employee add(Employee value) throws SQLException {
        String query = "INSERT INTO empleados VALUES (?, ?, ?, ?, ?)";
            db.open();
                var result = db.insert(query,value.getName(),value.getEmail(),value.getPassword(),
                        value.getBirthDate().toString(),value.isIsAdmin())
                        .orElseThrow(() -> new SQLException("Error al añadir un empleado"));
            db.close();
                if (result.next()){
                    list.add(value);
                    return value;
                }

        return null;
    }

    @Override
    public Employee remove(Employee value) throws SQLException {
        String query = "DELETE FROM empleados WHERE email=?";
            db.open();
                var result = db.delete(query,value.getEmail());
                if (result>0){
                    list.remove(value);
                    return value;
                }
            db.close();
        return null;
    }

    @Override
    public ObservableList<Employee> findAll() {
        return list;
    }

    @Override
    public void removeAll() throws SQLException {
        String query="DELETE FROM empleados";
        db.open();
        db.delete(query);
        list.removeAll();
        db.close();
    }

    @Override
    public Employee findByEmail(String email) {
        var find = list.stream().filter(a -> a.getEmail().equals(email)).findFirst();
        return find.orElse(null);
    }
}
