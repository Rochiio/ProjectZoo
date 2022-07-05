package com.example.zoojava.repositories.employees;

import com.example.zoojava.models.Employee;
import com.example.zoojava.repositories.ICRUD;

public interface EmployeesRepository extends ICRUD<Employee> {
    Employee findByEmail(String email);
}
