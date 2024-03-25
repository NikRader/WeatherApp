package com.example.weatherapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.daysBtn.setOnClickListener {
            binding.conteiner.setBackgroundColor(Color.parseColor("#FF000000"))
        }
        binding.hoursBtn.setOnClickListener {
            binding.conteiner.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }

    }

}



