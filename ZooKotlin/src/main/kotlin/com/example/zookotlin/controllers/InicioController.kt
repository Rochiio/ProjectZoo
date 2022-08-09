package com.example.zookotlin.controllers

import com.example.zookotlin.ZooApplication
import com.example.zookotlin.managers.SceneManager
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.net.URL

/**
 * Controlador para la vista inicio-view.
 * Para la elección de ver a los animales o empleados del zoológico.
 */
class InicioController {
    @FXML
    private lateinit var btnEmployee: Button

    @FXML
    private lateinit var btnAnimals: Button


    @FXML
    fun initialize() {
        val linkEmployee: URL = ZooApplication::class.java.getResource("icons/empleados.png") as URL
        val linkAnimals: URL = ZooApplication::class.java.getResource("icons/animales.png") as URL
        val employees = Image(linkEmployee.toString(), 60.0, 60.0, false, false)
        val animals = Image(linkAnimals.toString(), 60.0, 60.0, false, false)
        btnAnimals.graphic = ImageView(animals)
        btnEmployee.graphic = ImageView(employees)
    }


    /**
     * Cambiar a la escena de animales.
     */
    fun changeToAnimals() {
        SceneManager.animalsScene()
    }


    /**
     * Cambiar a la escena de empleados.
     */
    fun changeToEmployees() {
        SceneManager.employeesScene()
    }
}