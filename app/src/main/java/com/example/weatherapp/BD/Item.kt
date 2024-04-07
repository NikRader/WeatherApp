package com.example.weatherapp.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewWeather")
data class Item(
    // Для автоматической генерации id
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    @ColumnInfo(name = "name") // Cоздание столбца
    var name: String = "",

    @ColumnInfo(name = "price") // Cоздание столбца
    var price: String ="",

)
