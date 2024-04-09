package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.weatherapp.DB.MainDb
import com.example.weatherapp.databinding.ActivityMyQueryBinding


class MyQuery : AppCompatActivity() {
    private lateinit var binding: ActivityMyQueryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyQueryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this) { list ->
            binding.tvList.text = ""
            list.forEach {
                val text = "Id: ${it.id} City: ${it.city} Time: ${it.time}" +
                        " Condition: ${it.condition} Current Temperature: ${it.time}\n"
                binding.tvList.append(text)
            }
        }


    }
}
