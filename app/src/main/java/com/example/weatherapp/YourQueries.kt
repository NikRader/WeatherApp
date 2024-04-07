package com.example.weatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.BD.Item
import com.example.weatherapp.BD.MainDB
import com.example.weatherapp.databinding.ActivityYourQueriesBinding

class YourQueries : AppCompatActivity() {
    private lateinit var binding : ActivityYourQueriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourQueriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDB.getDB(this)
        binding.saveBtn.setOnClickListener{
           val item = Item(
               null, // БД сама присвоит id
               binding.edit1.text.toString(),
               binding.edit2.text.toString()
           )
            Thread{ // Нельзя  работать с БД на основном (UI) потоке
                db.getDao().insertItem(item)
            }.start()

        }

    }
}