package com.example.zookotlin.repositories.animals

import com.example.zookotlin.models.Animal
import com.example.zookotlin.models.enums.typeAnimal
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import java.time.LocalDate

internal class AnimalsRepositoryImplTest {

    private val repository: AnimalsRepository? = AnimalsRepositoryImpl.getInstance()
    private val test: Animal = Animal(1, "Kuno", typeAnimal.MAMIFEROS, LocalDate.now(), null)


    @AfterEach
    fun tearDown() {
        repository!!.removeAll()
    }

    @BeforeEach
    fun setUp() {
        repository!!.removeAll()
    }

    @Test
    fun add() {
        val add = repository!!.add(test)
        assertAll(
            Executable { assertEquals(add!!.getName(), test.getName()) },
            Executable { assertEquals(add!!.getType(), test.getType()) },
            Executable { assertEquals(add!!.getBirthDate(), test.getBirthDate()) },
            Executable { assertEquals(add!!.getImg(), test.getImg()) }
        )
    }

    @Test
    fun remove() {
        val add= repository!!.add(test)
        val remove = repository.remove(add!!)
        assertAll(
            Executable { assertEquals(remove!!.getId(), add.getId()) },
            Executable { assertEquals(remove!!.getName(), add.getName()) },
            Executable { assertEquals(remove!!.getType(), add.getType()) },
            Executable { assertEquals(remove!!.getBirthDate(), add.getBirthDate()) },
            Executable { assertEquals(remove!!.getImg(), add.getImg()) }
        )
    }

    @Test
    fun findAll() {
        val add = repository!!.add(test)
        val lista = repository.findAll()
        assertAll(
            Executable { assertEquals(1, lista.size) },
            Executable { assertEquals(lista[0].getId(), add!!.getId()) },
            Executable { assertEquals(lista[0].getName(), add!!.getName()) },
            Executable { assertEquals(lista[0].getType(), add!!.getType()) },
            Executable { assertEquals(lista[0].getBirthDate(), add!!.getBirthDate()) },
            Executable { assertEquals(lista[0].getImg(), add!!.getImg()) }
        )
    }

    @Test
    fun findById() {
        val add  = repository!!.add(test)
        val find = repository.findById(add!!.getId())
        assertAll(
            Executable { assertEquals(find.getId(), add.getId()) },
            Executable { assertEquals(find.getName(), add.getName()) },
            Executable { assertEquals(find.getType(), add.getType()) },
            Executable { assertEquals(find.getBirthDate(), add.getBirthDate()) },
            Executable { assertEquals(find.getImg(), add.getImg()) }
        )
    }

    @Test
    fun modifyById() {
        val add = repository!!.add(test)
        add!!.setName("cambiado")
        val modify = repository.modifyById(add.getId(), add)
        assertAll(
            Executable { assertEquals(modify!!.getId(), add.getId()) },
            Executable { assertEquals(modify!!.getName(), add.getName()) },
            Executable { assertEquals(modify!!.getType(), add.getType()) },
            Executable { assertEquals(modify!!.getBirthDate(), add.getBirthDate()) },
            Executable { assertEquals(modify!!.getImg(), add.getImg()) }
        )
    }
}