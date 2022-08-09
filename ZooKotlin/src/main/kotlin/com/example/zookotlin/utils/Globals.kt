package com.example.zookotlin.utils

import com.example.zookotlin.models.Employee
import java.io.File
import java.nio.file.Paths

object Globals {
    lateinit var globalEmployee: Employee
    private val CURRENT = Paths.get("")
    val DIR_CSV = CURRENT.toAbsolutePath().toString() + File.separator + "csv"
    val ANIMALS_CSV = DIR_CSV + File.separator + "animales.csv"
    val EMPLOYEE_CSV = DIR_CSV + File.separator + "empleados.csv"
}