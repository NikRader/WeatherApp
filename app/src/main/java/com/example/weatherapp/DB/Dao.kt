package com.example.weatherapp.DB
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item: Item)
    @Query("SELECT * FROM weather_requests")
    fun getAllItem(): Flow<List<Item>>
}