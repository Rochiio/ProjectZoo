package com.example.zoojava.repositories.employees;

import com.example.zoojava.models.Employee;
import com.example.zoojava.repositories.ICRUD;
import javafx.collections.ObservableList;


public interface EmployeesRepository extends ICRUD<Employee> {
    ObservableList<Employee> findAll();
    Employee findByEmail(String email);
}
