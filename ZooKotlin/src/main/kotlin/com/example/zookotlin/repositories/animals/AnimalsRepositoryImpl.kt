package com.example.zookotlin.repositories.animals

import com.example.zookotlin.managers.DataBaseManager
import com.example.zookotlin.models.Animal
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.sql.SQLException

/**
 * Repositorio de animales del programa, implementado con una base de datos sqlite y con lista de observables.
 * En algunos métodos uso directamente la lista para ahorranos entradas a la base de datos.
 */
class AnimalsRepositoryImpl: AnimalsRepository {
    private val list: ObservableList<Animal> = FXCollections.observableArrayList()
    private val db: DataBaseManager = DataBaseManager.getInstance()


    companion object {
        private var instance: AnimalsRepositoryImpl? = null

        fun getInstance(): AnimalsRepositoryImpl? {
            if (instance == null) {
                instance = AnimalsRepositoryImpl()
            }
            return instance
        }
    }


    @Throws(SQLException::class)
    override fun add(value: Animal): Animal? {
        val query = "INSERT INTO animales VALUES (null, ?, ?, ?, ?)"
        db.open()
        val result = db.insert(
            query, value.getName(), value.getType().toString(), value.getBirthDate().toString(), value.getImg()
        ).orElseThrow { SQLException("Error al insertar animal") }
        if (result.next()) {
            value.setId(result.getInt(1))
            list.add(value)
            db.close()
            return value
        }
        return null
    }

    @Throws(SQLException::class)
    override fun remove(value: Animal): Animal? {
        val query = "DELETE FROM animales WHERE id=?"
        db.open()
        val result = db.delete(query, value.getId())
        db.close()
        if (result > 0) {
            list.remove(value)
            return value
        }
        return null
    }

    override fun findAll(): ObservableList<Animal> {
        return list
    }

    @Throws(SQLException::class)
    override fun removeAll() {
        val query = "DELETE FROM animales"
        db.open()
        db.delete(query)
        list.removeAll()
        db.close()
    }

    override fun findById(id: Int): Animal {
        return list.filtered { a: Animal -> a.getId() == id }.first()
    }

    @Throws(SQLException::class)
    override fun modifyById(id: Int, newAnimal: Animal): Animal? {
        val query = "UPDATE animales SET name=?, type=?, birthDate=?, img=? WHERE id=?"
            db.open()
                val result = db.update(
                    query, newAnimal.getName(), newAnimal.getType(), newAnimal.getBirthDate(),
                    newAnimal.getImg(), id
                )
            db.close()
         if (result > 0) {
            return newAnimal
        }
        return null
    }


}