package com.example.zoojava.repositories.animals;

import com.example.zoojava.models.Animal;
import com.example.zoojava.models.Employee;
import com.example.zoojava.models.enums.typeAnimal;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test repositorio de animales.
 * Las id de los animales son llevadas por la base de datos por lo que no sabremos su id hasta añadir al animal.
 */
class AnimalsRepositoryImplTest {

    private final AnimalsRepository repository = new AnimalsRepositoryImpl();
    private final Animal test = new Animal(1,"Kuno", typeAnimal.MAMIFEROS,LocalDate.now());


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
                () -> assertEquals(add.getType(),test.getType()),
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
    void findById() {
    }

    @Test
    void modifyById() {
    }
}