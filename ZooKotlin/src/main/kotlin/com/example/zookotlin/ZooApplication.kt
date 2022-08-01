package com.example.zookotlin

import com.example.zookotlin.managers.SceneManager
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class ZooApplication : Application() {
    override fun start(stage: Stage) {
       SceneManager.loginScene(stage)
    }
}

fun main() {
    Application.launch(ZooApplication::class.java)
}