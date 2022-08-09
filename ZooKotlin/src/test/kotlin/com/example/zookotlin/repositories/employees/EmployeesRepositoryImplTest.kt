package com.example.zookotlin.repositories.employees

import com.example.zookotlin.models.Employee
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.time.LocalDate

internal class EmployeesRepositoryImplTest {
    private val repository: EmployeesRepository = EmployeesRepositoryImpl.getInstance()!!
    private val test: Employee = Employee("Paco", "paco.paquito@gmail.com", "12345", LocalDate.now(), true)


    @AfterEach
    fun tearDown() {
        repository.removeAll()
    }


    @BeforeEach
    fun setUp() {
        repository.removeAll()
    }

    @Test
    fun add() {
        val add = repository.add(test)
        assertAll(
            Executable { assertEquals(add!!.getName(), test.getName()) },
            Executable { assertEquals(add!!.getEmail(), test.getEmail()) },
            Executable { assertEquals(add!!.getPassword(), test.getPassword()) },
            Executable { assertEquals(add!!.getBirthDate(), test.getBirthDate()) }
        )
    }


    @Test
    fun remove() {
        val add = repository.add(test)
        val eliminate = repository.remove(test)
        assertAll(
            Executable { assertEquals(eliminate!!.getName(), test.getName()) },
            Executable { assertEquals(eliminate!!.getEmail(), test.getEmail()) },
            Executable { assertEquals(eliminate!!.getPassword(), test.getPassword()) },
            Executable { assertEquals(eliminate!!.getBirthDate(), test.getBirthDate()) }
        )
    }

    @Test
    fun findAll() {
        repository.add(test)
        val list = repository.findAll()
        assertAll(
            Executable { assertEquals(1, list.size) },
            Executable { assertEquals(list[0].getName(), test.getName()) },
            Executable { assertEquals(list[0].getEmail(), test.getEmail()) },
            Executable { assertEquals(list[0].getPassword(), test.getPassword()) },
            Executable { assertEquals(list[0].getBirthDate(), test.getBirthDate()) }
        )
    }

    @Test
    fun findByEmail() {
        repository.add(test)
        val find = repository.findByEmail(test.getEmail())
        assertAll(
            Executable { assertEquals(find!!.getName(), test.getName()) },
            Executable { assertEquals(find!!.getEmail(), test.getEmail()) },
            Executable { assertEquals(find!!.getPassword(), test.getPassword()) },
            Executable { assertEquals(find!!.getBirthDate(), test.getBirthDate()) }
        )
    }


    @Test
    fun findByEmailNull() {
        val find = repository.findByEmail(test.getEmail())
        assertNull(find)
    }
}