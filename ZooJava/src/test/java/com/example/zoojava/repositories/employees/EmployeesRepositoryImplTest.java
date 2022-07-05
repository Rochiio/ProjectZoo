package com.example.zoojava.repositories.employees;

import com.example.zoojava.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesRepositoryImplTest {
    private EmployeesRepository repository = new EmployeesRepositoryImpl();
    private Employee test = new Employee("Paco","paco.paquito@gmail.com","12345", LocalDate.now(),true);


    @AfterEach
    void tearDown() {
        repository.removeAll();
    }

    @Test
    void add() throws SQLException {
        var add = repository.add(test);
        assertAll(
                () -> assertEquals(add.getName(),test.getName()),
                () -> assertEquals(add.getEmail(),test.getEmail()),
                () -> assertEquals(add.getPassword(),test.getPassword()),
                () -> assertEquals(add.getBirthDate(),test.getBirthDate())
                );
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