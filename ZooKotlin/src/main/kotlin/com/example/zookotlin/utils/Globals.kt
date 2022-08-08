package com.example.zookotlin.utils

import com.example.zookotlin.models.Employee
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

object Globals {
    lateinit var globalEmployee :Employee
    private val CURRENT: Path = Paths.get("")
    val DIR_CSV: String = (CURRENT.toAbsolutePath() + File.separator + "csv").toString()
    val ANIMAL_CSV: String = DIR_CSV + File.separator + "animales.csv"
    val EMPLOYEE_CSV: String = DIR_CSV + File.separator + "empleados.csv"
}