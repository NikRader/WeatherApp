package com.example.weatherapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.fragments.DaysFragment
import com.example.weatherapp.fragments.HoursFragment
import com.example.weatherapp.fragments.MainFragment
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        days_hours_btn()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeCurrentCard, MainFragment.newInstance())
            .commit()
    }
    private fun days_hours_btn() = with(binding){
        hoursBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeForecast, HoursFragment.newInstance())
                .commit()
        }
        daysBtn.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.placeForecast, DaysFragment.newInstance())
                .commit()
        }
    }
}