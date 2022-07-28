package com.example.zoojava.utils;

import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.animals.AnimalsRepositoryImpl;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.repositories.employees.EmployeesRepositoryImpl;
import com.example.zoojava.utils.csv.ImportAnimalCsv;
import com.example.zoojava.utils.csv.ImportAnimalCsvImpl;
import com.example.zoojava.utils.csv.ImportEmployeeCsv;
import com.example.zoojava.utils.csv.ImportEmployeeCsvImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class DataSystem {
    private ImportAnimalCsv  animalCsv= new ImportAnimalCsvImpl();
    private ImportEmployeeCsv employeeCsv= new ImportEmployeeCsvImpl();
    private AnimalsRepository animalsRepository= AnimalsRepositoryImpl.getInstance();
    private EmployeesRepository employeesRepository= EmployeesRepositoryImpl.getInstance();




    /**
     * Añadir los datos necesarios.
     */
    public void addData(){
        addAnimalData();
        addEmployeeData();
    }


    /**
     * Añadir los datos de los empleados
     */
    private void addEmployeeData() {
        var listEmployee = employeeCsv.importData();
        listEmployee.forEach((a) -> {
            try {
                employeesRepository.add(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /**
     * Añadir los datos de los animales
     */
    private void addAnimalData() {
        var listAnimals = animalCsv.importData();


        listAnimals.forEach((a) -> {
            try {
                animalsRepository.add(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void backup() {
        backupAnimals();
        backupEmployees();
    }

    private void backupEmployees() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(Globals.EMPLOYEE_CSV, false))) {
            var employees = employeesRepository.findAll();
            pw.println("name;email;password;birthdate;isAdmin");
            employees.forEach((a) -> pw.println(a.toCsv()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Para hacer backup de los animales al terminar de utilizar el programa.
     */
    private void backupAnimals() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(Globals.ANIMALS_CSV, false))) {
            var animals = animalsRepository.findAll();
            pw.println("id;name;type;birthdate;img");
            animals.forEach((a) -> pw.println(a.toCsv()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
