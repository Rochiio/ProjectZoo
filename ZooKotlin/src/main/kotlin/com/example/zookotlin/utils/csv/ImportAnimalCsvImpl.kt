package com.example.zookotlin.utils.csv

import com.example.zookotlin.models.Animal
import com.example.zookotlin.models.enums.typeAnimal
import com.example.zookotlin.utils.Globals
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.stream.Collectors

class ImportAnimalCsvImpl: ImportAnimalCsv {
    override fun importData(): List<Animal> {
        var list: MutableList<Animal> = Files.lines(Path.of(Globals.ANIMAL_CSV)).skip(1).map { line: String ->
            this.changeToAnimal(
                line
            )
        }.collect(Collectors.toList())
        return list
    }


    private fun changeToAnimal(line: String): Animal {
        val campos = line.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val id = campos[0].toInt()
        val name = campos[1]
        val type = typeAnimal.valueOf(campos[2])
        val birthDate = parseFecha(campos[3])
        val img = campos[4]
        return Animal(id, name, type, birthDate, img)
    }


    private fun parseFecha(campo: String): LocalDate {
        val fecha = campo.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val dia = fecha[2].toInt()
        val mes = fecha[1].toInt()
        val year = fecha[0].toInt()
        return LocalDate.of(year, mes, dia)
    }
}