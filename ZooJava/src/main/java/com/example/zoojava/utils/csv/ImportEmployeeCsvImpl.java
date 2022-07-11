package com.example.zoojava.utils.csv;

import com.example.zoojava.models.Employee;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ImportEmployeeCsvImpl implements ImportEmployeeCsv {
    private final Path CURRENT = Paths.get("user.dir");
    private final String DIR = CURRENT.toAbsolutePath() + File.separator + "csv";
    private final String FILE_CSV = DIR + File.separator + "empleados.csv";


    @Override
    public List<Employee> importData() {
        List<Employee> lista;
        try {
            lista= Files.lines(Path.of(FILE_CSV)).skip(1).map(this::changeToEmployee).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    private Employee changeToEmployee(String line){
        var campos = line.split(",");
        var name = campos[0];
        var email = campos[1];
        var password = campos[2];
        var birthDate = LocalDate.parse(campos[3]);
        var isAdmin = Boolean.parseBoolean(campos[4]);
        return new Employee(name, email, password,birthDate,isAdmin);
    }
}
