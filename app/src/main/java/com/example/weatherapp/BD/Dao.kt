package com.example.weatherapp.BD


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)
    @Query("SELECT * FROM NewWeather")
    fun getAllItems() : Flow<List<Item>>  // использовали Flow (чтобы не работать на второстепенном потокеЫ)
}