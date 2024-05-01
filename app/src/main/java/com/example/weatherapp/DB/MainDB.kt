package com.example.weatherapp.DB

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.Requests
import com.example.weatherapp.fragments.MainFragment

@Database (entities = [Item::class], version = 3)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: MainFragment): MainDb{
            return Room.databaseBuilder(
                context.requireContext(),
                MainDb::class.java,
                "test3.db"
            ).build()
        }

        fun getDb(context: Requests): MainDb {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "test3.db"
            ).build()
        }
    }
}