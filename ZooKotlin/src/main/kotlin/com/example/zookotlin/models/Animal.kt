package com.example.zookotlin.models

import com.example.zookotlin.models.enums.typeAnimal
import javafx.beans.property.*
import java.time.LocalDate

/**
 * Clase creacion modelo aniamles.
 */
class Animal {
    private var id: IntegerProperty
    private var name: StringProperty
    private var type: ObjectProperty<typeAnimal>
    private var birthDate: ObjectProperty<LocalDate>
    private var img: StringProperty


    constructor(id: Int, name: String, type: typeAnimal, birthDate: LocalDate, img: String){
        this.id = SimpleIntegerProperty(id)
        this.name = SimpleStringProperty(name)
        this.type = SimpleObjectProperty(type)
        this.birthDate = SimpleObjectProperty(birthDate)
        this.img = SimpleStringProperty(img)
    }


    fun setId(id: Int) {
        this.id.set(id)
    }

    fun getId(): Int {
        return id.get()
    }

    fun idProperty(): IntegerProperty {
        return id
    }


    fun getName(): String {
        return name.get()
    }

    fun nameProperty(): StringProperty {
        return name
    }

    fun setName(name: String) {
        this.name.set(name)
    }

    fun getType(): typeAnimal {
        return type.get()
    }

    fun typeProperty(): ObjectProperty<typeAnimal> {
        return type
    }

    fun setType(type: typeAnimal?) {
        this.type.set(type)
    }

    fun getBirthDate(): LocalDate {
        return birthDate.get()
    }

    fun birthDateProperty(): ObjectProperty<LocalDate> {
        return birthDate
    }

    fun setBirthDate(birthDate: LocalDate?) {
        this.birthDate.set(birthDate)
    }

    fun getImg(): String {
        return img.get()
    }

    fun imgProperty(): StringProperty {
        return img
    }

    fun setImg(img: String) {
        this.img.set(img)
    }


    /**
     * Para pasar a csv todos los animales.
     */
    fun toCsv(): String {
        return getId().toString() + ";" + getName() + ";" + getType().toString() + ";" + getBirthDate().toString() + ";" + getImg()
    }
}