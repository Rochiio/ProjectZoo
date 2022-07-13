package com.example.zoojava.utils.csv;

import com.example.zoojava.models.Animal;
import com.example.zoojava.models.enums.typeAnimal;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ImportAnimalCsvImpl implements ImportAnimalCsv {
    private final Path CURRENT = Paths.get("");
    private final String DIR = CURRENT.toAbsolutePath() + File.separator + "csv";
    private final String FILE_CSV = DIR + File.separator + "animales.csv";


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
        var campos = line.split(";");
        var id = Integer.parseInt(campos[0]);
        var name = campos[1];
        var type = typeAnimal.valueOf(campos[2]);
        var birthDate = parseFecha(campos[3]);
        var img = campos[4];
        return new Animal(id, name, type, birthDate,img);
    }


    private LocalDate parseFecha(String campo) {
        var fecha = campo.split("/");
        var dia = Integer.parseInt(fecha[0]);
        var mes = Integer.parseInt(fecha[1]);
        var year = Integer.parseInt(fecha[2]);
        return LocalDate.of(year,mes,dia);
    }
}
