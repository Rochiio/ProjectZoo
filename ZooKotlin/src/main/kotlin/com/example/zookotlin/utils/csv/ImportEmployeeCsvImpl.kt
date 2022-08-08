package com.example.zookotlin.utils.csv

import com.example.zookotlin.models.Employee
import com.example.zookotlin.utils.Globals
import java.lang.Boolean
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate
import java.util.stream.Collectors
import kotlin.String


class ImportEmployeeCsvImpl: ImportEmployeeCsv {


    override fun importData(): List<Employee> {
        var list = Files.lines(Path.of(Globals.EMPLOYEE_CSV)).skip(1).map { line:String ->
            changeToEmployee(
                line
            )
        }.collect(Collectors.toList())
        return list
    }


    private fun changeToEmployee(line: String): Employee {
        val campos = line.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val name = campos[0]
        val email = campos[1]
        val password = campos[2]
        val birthDate = parseFecha(campos[3])
        val isAdmin = Boolean.parseBoolean(campos[4])
        return Employee(name, email, password, birthDate, isAdmin)
    }


    private fun parseFecha(campo: String): LocalDate {
        val fecha = campo.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val dia = fecha[2].toInt()
        val mes = fecha[1].toInt()
        val year = fecha[0].toInt()
        return LocalDate.of(year, mes, dia)
    }
}