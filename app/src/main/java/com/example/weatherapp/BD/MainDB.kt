package com.example.weatherapp.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1 )
abstract class MainDB: RoomDatabase() {
    abstract fun getDao() : Dao // функция для получения интерфейса Dao
   companion object{
       // Функция, которая возвращает MainDB
       fun getDB(context: Context): MainDB{
           return Room.databaseBuilder(
                context.applicationContext,
               MainDB::class.java,
               "Weather.db"
           ).build()
       }
   }
}