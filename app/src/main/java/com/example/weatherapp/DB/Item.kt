package com.example.weatherapp.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "weather_requests")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "city")
    var city: String,
    @ColumnInfo(name = "time")
    var time: String,
    @ColumnInfo(name = "condition")
    var condition: String,
    @ColumnInfo(name = "currentTemp")
    var currentTemp: String,
    @ColumnInfo(name = "maxTemp")
    var maxTemp: String,
    @ColumnInfo(name = "minTemp")
    var minTemp: String,
)