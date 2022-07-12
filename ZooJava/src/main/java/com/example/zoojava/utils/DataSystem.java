package com.example.zoojava.utils;

import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.utils.csv.ImportAnimalCsv;
import com.example.zoojava.utils.csv.ImportEmployeeCsv;

import javax.inject.Inject;
import java.sql.SQLException;


public class DataSystem {
    private ImportAnimalCsv animalCsv;
    private ImportEmployeeCsv employeeCsv;
    private AnimalsRepository animalsRepository;
    private EmployeesRepository employeesRepository;

    @Inject
    public DataSystem(ImportAnimalCsv animalCsv, ImportEmployeeCsv employeeCsv,
                      AnimalsRepository animalsRepository, EmployeesRepository employeesRepository) {
        this.animalCsv = animalCsv;
        this.employeeCsv = employeeCsv;
        this.employeesRepository = employeesRepository;
        this.animalsRepository = animalsRepository;
    }


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



}
