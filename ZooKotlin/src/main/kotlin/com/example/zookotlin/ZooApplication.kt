package com.example.zookotlin

import com.example.zookotlin.managers.SceneManager
import com.example.zookotlin.utils.DataSystem
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

/**
 * @author Rocío P.F
 * @version 1.0
 * github: Rochiio
 * Programa para la administración de un zoológico, haciendo test.
 */
class ZooApplication : Application() {
    override fun start(stage: Stage) {
       SceneManager.loginScene(stage)
    }
}

fun main() {
    val system = DataSystem()
    system.addData()
    Application.launch(ZooApplication::class.java)
    system.backupAndClean()
}