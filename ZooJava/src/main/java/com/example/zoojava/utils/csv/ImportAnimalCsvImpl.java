package com.example.zoojava.utils.csv;

import com.example.zoojava.models.Animal;
import com.example.zoojava.models.enums.typeAnimal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ImportAnimalCsvImpl implements ImportAnimalCsv {
    private final Path CURRENT = Paths.get("user.dir");
    private final String DIR = CURRENT.toAbsolutePath() + File.separator + "csv";
    private final String FILE_CSV = DIR + File.separator + "empleados.csv";


    @Override
    public List<Animal> importData() {
        List<Animal> lista;
        try {
            lista= Files.lines(Path.of(FILE_CSV)).skip(1).map(this::changeToAnimal).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    private Animal changeToAnimal(String line){
        var campos = line.split(",");
        var id = Integer.parseInt(campos[0]);
        var name = campos[1];
        var type = typeAnimal.valueOf(campos[2]);
        var birthDate = LocalDate.parse(campos[3]);
        return new Animal(id, name, type, birthDate);
    }
}
