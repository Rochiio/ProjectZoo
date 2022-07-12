package com.example.zoojava.utils;

import com.example.zoojava.DI.components.DaggerAnimalCsvComponent;
import com.example.zoojava.DI.components.DaggerAnimalRepositoryComponent;
import com.example.zoojava.DI.components.DaggerEmployeeCsvComponent;
import com.example.zoojava.DI.components.DaggerEmployeeRepositoryComponent;
import com.example.zoojava.repositories.animals.AnimalsRepository;
import com.example.zoojava.repositories.employees.EmployeesRepository;
import com.example.zoojava.utils.csv.ImportAnimalCsv;
import com.example.zoojava.utils.csv.ImportEmployeeCsv;

import java.sql.SQLException;


public class DataSystem {
    private ImportAnimalCsv animalCsv= DaggerAnimalCsvComponent.create().build();
    private ImportEmployeeCsv employeeCsv= DaggerEmployeeCsvComponent.create().build();
    private AnimalsRepository animalsRepository= DaggerAnimalRepositoryComponent.create().build();
    private EmployeesRepository employeesRepository= DaggerEmployeeRepositoryComponent.create().build();




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
