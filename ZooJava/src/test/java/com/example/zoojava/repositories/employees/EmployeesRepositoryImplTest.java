package com.example.zoojava.repositories.employees;

import com.example.zoojava.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesRepositoryImplTest {
    private EmployeesRepository repository = new EmployeesRepositoryImpl();
    private Employee test = new Employee("Paco","paco.paquito@gmail.com","12345", LocalDate.now(),true);


    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByEmail() {
    }
}