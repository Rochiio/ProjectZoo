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
    void remove() throws SQLException {
        var add = repository.add(test);
        var remove = repository.remove(add);
        assertAll(
                () -> assertEquals(remove.getId(),add.getId()),
                () -> assertEquals(remove.getName(),add.getName()),
                () -> assertEquals(remove.getType(),add.getType()),
                () -> assertEquals(remove.getBirthDate(),add.getBirthDate())
        );
    }

    @Test
    void findAll() throws SQLException {
        var add = repository.add(test);
        var lista = repository.findAll();
        assertAll(
                () -> assertEquals(lista.size(),1),
                () -> assertEquals(lista.get(0).getId(),add.getId()),
                () -> assertEquals(lista.get(0).getName(),add.getName()),
                () -> assertEquals(lista.get(0).getType(),add.getType()),
                () -> assertEquals(lista.get(0).getBirthDate(),add.getBirthDate())
        );
    }

    @Test
    void findById() throws SQLException {
        var add = repository.add(test);
        var find = repository.findById(add.getId());
        assertAll(
                () -> assertEquals(find.getId(),add.getId()),
                () -> assertEquals(find.getName(),add.getName()),
                () -> assertEquals(find.getType(),add.getType()),
                () -> assertEquals(find.getBirthDate(),add.getBirthDate())
        );
    }

    @Test
    void modifyById() throws SQLException {
        var add = repository.add(test);
        add.setName("cambiado");
        var modify = repository.modifyById(add.getId(),add);
        assertAll(
                () -> assertEquals(modify.getId(),add.getId()),
                () -> assertEquals(modify.getName(),add.getName()),
                () -> assertEquals(modify.getType(),add.getType()),
                () -> assertEquals(modify.getBirthDate(),add.getBirthDate())
        );
    }
}