package com.example.zoojava.repositories.employees;

import com.example.zoojava.models.Employee;
import com.example.zoojava.repositories.ICRUD;

import java.util.List;

public interface EmployeesRepository extends ICRUD<Employee> {
    List<Employee> findAll();
    Employee findByEmail(String email);
}
