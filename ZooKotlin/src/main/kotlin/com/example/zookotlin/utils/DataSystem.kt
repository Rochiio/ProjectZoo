package com.example.zookotlin.utils

import com.example.zookotlin.repositories.animals.AnimalsRepository
import com.example.zookotlin.repositories.animals.AnimalsRepositoryImpl
import com.example.zookotlin.repositories.employees.EmployeesRepository
import com.example.zookotlin.repositories.employees.EmployeesRepositoryImpl
import com.example.zookotlin.utils.csv.ImportAnimalCsv
import com.example.zookotlin.utils.csv.ImportAnimalCsvImpl
import com.example.zookotlin.utils.csv.ImportEmployeeCsv
import com.example.zookotlin.utils.csv.ImportEmployeeCsvImpl
import java.io.FileWriter
import java.io.PrintWriter

/**
 * Clase para el manejo de daots tanto como añadirlos o la limpieza de estos
 */
class DataSystem {
    private var animalCsv: ImportAnimalCsv = ImportAnimalCsvImpl()
    private var employeeCsv: ImportEmployeeCsv = ImportEmployeeCsvImpl()
    private var animalsRepository: AnimalsRepository = AnimalsRepositoryImpl.getInstance()!!
    private var employeeRepository: EmployeesRepository = EmployeesRepositoryImpl.getInstance()!!


    /**
     * Añadir los datos necesarios
     */
    fun addData(){
        addAnimalData()
        addEmployeeData()
    }


    /**
     * Añadir los datos de los empleados
     */
    private fun addEmployeeData() {
        val listEmployees = employeeCsv.importData()
        listEmployees.forEach {a -> employeeRepository.add(a)}
    }


    /**
     * Añadir los datos de los animales
     */
    private fun addAnimalData() {
        val listAnimals = animalCsv.importData()
        listAnimals.forEach {a -> animalsRepository.add(a) }
    }


    /**
     * Backup de los aniumales y empleados.
     * Limpieza de la base de datos.
     * Al cerrar el programa
     */
    fun backupAndClean(){
        backupAnimals()
        backupEmployees()
        cleanDataBase()
    }


    /**
     * Limpieza de la base de datos.
     */
    private fun cleanDataBase() {
        animalsRepository.removeAll()
        employeeRepository.removeAll()
    }

    /**
     * Backup de los empleados en CSV.
     */
    private fun backupEmployees() {
        val pw = PrintWriter(FileWriter(Globals.EMPLOYEE_CSV,false))
        val employees = employeeRepository.findAll()
        pw.println("name;email;password;birthdate;isAdmin")
        employees.forEach {a -> pw.println(a.toCsv())}
    }


    /**
     * Backup de los animales en CSV.
     */
    private fun backupAnimals() {
        val pw = PrintWriter(FileWriter(Globals.ANIMAL_CSV,false))
        val animals = animalsRepository.findAll()
        pw.println("id;name;type;birthdate;img")
        animals.forEach {a -> pw.println(a.toCsv())}
    }
}