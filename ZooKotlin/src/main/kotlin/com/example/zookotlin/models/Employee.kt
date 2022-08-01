package com.example.zookotlin.models

import javafx.beans.property.*
import java.time.LocalDate

/**
 * Clase creacion modelo empleados.
 */
class Employee {
    private var name: StringProperty
    private var email: StringProperty
    private var password:StringProperty
    private var birthDate: ObjectProperty<LocalDate>
    private var isAdmin: BooleanProperty

    constructor(name: String, email:String, password:String, birthDate: LocalDate, isAdmin: Boolean){
        this.name = SimpleStringProperty(name)
        this.email = SimpleStringProperty(email)
        this.password = SimpleStringProperty(password)
        this.birthDate= SimpleObjectProperty(birthDate)
        this.isAdmin = SimpleBooleanProperty(isAdmin)
    }


    fun getName(): String {
        return name.get()
    }

    fun nameProperty(): StringProperty{
        return name
    }

    fun setName(name: String) {
        this.name.set(name)
    }

    fun getEmail(): String {
        return email.get()
    }

    fun emailProperty(): StringProperty {
        return email
    }

    fun setEmail(email: String) {
        this.email.set(email)
    }

    fun getPassword(): String{
        return password.get()
    }

    fun passwordProperty(): StringProperty {
        return password
    }

    fun setPassword(password: String) {
        this.password.set(password)
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

    fun isIsAdmin(): Boolean {
        return isAdmin.get()
    }

    fun isAdminProperty(): BooleanProperty {
        return isAdmin
    }

    fun setIsAdmin(isAdmin: Boolean) {
        this.isAdmin.set(isAdmin)
    }

    fun toCsv():String{
        return getName() + ";" + getEmail() + ";" + getPassword() + ";" + getBirthDate().toString() + ";" + isIsAdmin()
    }
}