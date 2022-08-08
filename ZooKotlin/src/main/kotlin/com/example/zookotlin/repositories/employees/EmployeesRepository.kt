package com.example.zookotlin.repositories.employees

import com.example.zookotlin.models.Employee
import com.example.zookotlin.repositories.ICRUD
import javafx.collections.ObservableList

interface EmployeesRepository: ICRUD<Employee> {
    fun findAll(): ObservableList<Employee>
    fun findByEmail(email:String): Employee?
}