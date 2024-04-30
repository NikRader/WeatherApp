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
    }

    override fun onStart() {
        super.onStart()
        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this){
                binding.tvList.text = ""
                it.forEach {
                val text =
                        "  ${it.id}. ${it.city} Last update: ${it.time} Condition: ${it.condition} Current Temp.: ${it.currentTemp} Min Temp.: ${it.minTemp} Max Temp.: ${it.maxTemp} \n\n"
                binding.tvList.append(text)
                }
            }
        }


    }
