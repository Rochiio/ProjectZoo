package com.example.zookotlin.repositories.employees

import com.example.zookotlin.managers.DataBaseManager
import com.example.zookotlin.models.Employee
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.sql.SQLException

/**
 * Repositorio de empleados del programa, implementado con una base de datos sqlite y con lista de observables.
 * En algunos métodos uso directamente la lista para ahorranos entradas a la base de datos.
 */
class EmployeesRepositoryImpl: EmployeesRepository {
    private val list: ObservableList<Employee> = FXCollections.observableArrayList()
    private val db: DataBaseManager = DataBaseManager.getInstance()


    companion object {
        private var instance: EmployeesRepositoryImpl? = null

        fun getInstance(): EmployeesRepositoryImpl? {
            if (instance == null) {
                instance = EmployeesRepositoryImpl()
            }
            return instance
        }
    }

    override fun findAll(): ObservableList<Employee> {
        return list
    }

    override fun findByEmail(email: String): Employee? {
        return list.filtered { a: Employee -> a.getEmail() == email }.first()
    }

    override fun add(value: Employee): Employee? {
        val query="INSERT INTO empleados VALUES ( ?, ?, ?, ?, ?)"
            db.open()
                val result = db.insert(query, value.getName(), value.getEmail(), value.getPassword()
                    , value.getBirthDate().toString(), value.isIsAdmin()).orElseThrow { SQLException("Error al añadir empeado") }
        db.close()
        if (result.next()){
            list.add(value)
            return value
        }
        return  null
    }

    override fun remove(value: Employee): Employee? {
        val query = "DELETE FROM empleados WHERE email=?"
            db.open()
                val result = db.delete(query, value.getEmail())
            db.close()
                if (result>0){
                    list.remove(value)
                    return value
                }
        return null
    }

    override fun removeAll() {
        val query = "DELETE FROM empleados"
        db.open()
        db.delete(query)
        list.removeAll()
        db.close()
    }
}