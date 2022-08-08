package com.example.zookotlin.utils.csv

interface Import<T> {
    fun importData(): List<T>
}