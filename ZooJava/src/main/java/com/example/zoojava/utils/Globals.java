package com.example.zoojava.utils;

import com.example.zoojava.models.Employee;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public record Globals() {
    public static Employee globalEmployee;
    private static final Path CURRENT = Paths.get("");
    public static final String DIR_CSV = CURRENT.toAbsolutePath() + File.separator + "csv";
    public static final String ANIMALS_CSV = DIR_CSV + File.separator + "animales.csv";
    public static final String EMPLOYEE_CSV = DIR_CSV + File.separator + "empleados.csv";
}
