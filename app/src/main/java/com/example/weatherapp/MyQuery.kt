package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.weatherapp.DB.Item
import com.example.weatherapp.DB.MainDb
import com.example.weatherapp.databinding.ActivityMyQueryBinding


class MyQuery : AppCompatActivity() {
    lateinit var binding: ActivityMyQueryBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyQueryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this){ list->
            binding.tvList.text = ""
            list.forEach {
                val text = "Id: ${it.id} Id: ${it.time} Time: ${it.condition} Current Temp.: ${it.currentTemp} Min Temp.: ${it.minTemp} Max Temp.: ${it.maxTemp} \n"
                binding.tvList.append(text)
            }
        }
        binding.button2.setOnClickListener {
//
        }
    }
}