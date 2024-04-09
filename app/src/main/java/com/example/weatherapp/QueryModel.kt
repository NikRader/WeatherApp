package com.example.weatherapp

data class QueryModel(
    val city_query: String,
    val time_query: String,
    val condition_query: String,
    val currentTemp_query: String,
    val maxTemp_query: String,
    val minTemp_query: String,
)
