package com.example.zookotlin.repositories.animals

import com.example.zookotlin.models.Animal
import com.example.zookotlin.repositories.ICRUD
import javafx.collections.ObservableList

interface AnimalsRepository: ICRUD<Animal> {
    fun findAll(): ObservableList<Animal>
    fun findById(id: Int): Animal
    fun modifyById(id: Int, newAnimal: Animal):Animal?
}