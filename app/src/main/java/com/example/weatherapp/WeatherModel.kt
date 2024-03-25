package com.example.weatherapp

data class WeatherModel(
    val city: String,
    val time: String,
    val condition: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String,
    // Чтобы хранить информацию по часам
    val hours: String
)
