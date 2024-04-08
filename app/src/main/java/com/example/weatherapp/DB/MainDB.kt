package com.example.weatherapp.DB

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.fragments.MainFragment

@Database (entities = [Item::class], version = 2)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: MainFragment): MainDb{
            return Room.databaseBuilder(
                context.requireContext(),
                MainDb::class.java,
                "test2.db"
            ).build()
        }


    }
}