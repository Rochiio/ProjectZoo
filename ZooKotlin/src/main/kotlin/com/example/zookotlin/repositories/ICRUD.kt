package com.example.zookotlin.repositories

interface ICRUD<T> {
    fun add(value: T): T
    fun remove(value: T): T
    fun removeAll()
}