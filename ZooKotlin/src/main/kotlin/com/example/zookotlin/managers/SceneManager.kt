package com.example.zookotlin.managers

import com.example.zookotlin.ZooApplication
import com.example.zookotlin.utils.PropertiesNumbers
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

/**
 * Clase para manejar las diferentes escenas del programa.
 */
class SceneManager {
    
    companion object{

        /**
         * Cargar la escena de login de la aplicación.
         */
        fun loginScene(stage : Stage){
            val fxmlLoader = FXMLLoader(ZooApplication::class.java.getResource("views/login-view.fxml"))
            val scene = Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH, PropertiesNumbers.HEIGHT)
            stage.title="Login"
            stage.scene=scene
            stage.isResizable=false
            addIcon(stage)
            stage.show()
        }


        /**
         * Cargar la escena de acerca de.
         */
        fun openAbout(){
            val fxmlLoader = FXMLLoader(ZooApplication::class.java.getResource("views/about-view.fxml"))
            val scene = Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH_ABOUT, PropertiesNumbers.HEIGHT_ABOUT)
            val stage = Stage()
            stage.title="About"
            stage.scene=scene
            stage.isResizable=false
            addIcon(stage)
            stage.show()
        }


        /**
         * Cargar escena de inicio.
         */
        fun openInicio(stage: Stage){
            val fxmlLoader = FXMLLoader(ZooApplication::class.java.getResource("views/inicio-view.fxml"))
            val scene = Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH, PropertiesNumbers.HEIGHT)
            stage.title="Inicio"
            stage.scene=scene
            stage.isResizable=false
            addIcon(stage)
            stage.show()
        }


        /**
         * Cargar escena de administración de animales
         */
        fun animalsScene(){
            val fxmlLoader = FXMLLoader(ZooApplication::class.java.getResource("views/animals-view.fxml"))
            val scene = Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH,PropertiesNumbers.HEIGHT)
            val stage = Stage()
            stage.title="Animales"
            stage.scene=scene
            stage.isResizable=false
            addIcon(stage)
            stage.show()
        }


        /**
         * Cargar escena de administración de los empleados
         */
        fun employeesScene(){
            val fxmlLoader = FXMLLoader(ZooApplication::class.java.getResource("views/employees-view.fxml"))
            val scene = Scene(fxmlLoader.load(), PropertiesNumbers.WIDTH,PropertiesNumbers.HEIGHT)
            val stage = Stage()
            stage.title="Empleados"
            stage.scene=scene
            stage.isResizable=false
            addIcon(stage)
            stage.show()
        }


        /**
        * Para añadir a las escenas el icono.
         * @param stage stage a añadir el icono.
         */
        private fun addIcon(stage: Stage) {
            stage.icons.add(Image(ZooApplication::class.java.getResource("icons/zoo.png")!!.toString()))
        }
    }

}