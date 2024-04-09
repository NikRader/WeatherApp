package com.example.weatherapp.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.fragments.MainFragment

@Database (entities = [Item::class], version = 2)
abstract class MainDb : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "test.db"
            ).build()
        }


        }
    }
}