package com.example.zoojava.repositories.employees;

import com.example.zoojava.managers.DataBaseManager;
import com.example.zoojava.models.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesRepositoryImplTest {
    private final EmployeesRepository repository = EmployeesRepositoryImpl.getInstance();
    private final Employee test = new Employee("Paco","paco.paquito@gmail.com","12345", LocalDate.now(),true);


    @AfterEach
    void tearDown() {
        try {
            repository.removeAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeEach
    void setUp(){
        try {
            repository.removeAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    void remove() throws SQLException {
        var add = repository.add(test);
        var eliminate = repository.remove(test);
        assertAll(
                () -> assertEquals(eliminate.getName(),test.getName()),
                () -> assertEquals(eliminate.getEmail(),test.getEmail()),
                () -> assertEquals(eliminate.getPassword(),test.getPassword()),
                () -> assertEquals(eliminate.getBirthDate(),test.getBirthDate())
        );
    }

    @Test
    void findAll() throws SQLException {
        repository.add(test);
        var list = repository.findAll();
        assertAll(
                () -> assertEquals(list.size(),1),
                () -> assertEquals(list.get(0).getName(),test.getName()),
                () -> assertEquals(list.get(0).getEmail(),test.getEmail()),
                () -> assertEquals(list.get(0).getPassword(),test.getPassword()),
                () -> assertEquals(list.get(0).getBirthDate(),test.getBirthDate())
        );
    }

    @Test
    void findByEmail() throws SQLException {
        repository.add(test);
        var find = repository.findByEmail(test.getEmail());
        assertAll(
                () -> assertEquals(find.getName(),test.getName()),
                () -> assertEquals(find.getEmail(),test.getEmail()),
                () -> assertEquals(find.getPassword(),test.getPassword()),
                () -> assertEquals(find.getBirthDate(),test.getBirthDate())
        );
    }


    @Test
    void findByEmailNull() throws SQLException {
        var find = repository.findByEmail(test.getEmail());
        assertNull(find);
    }
}