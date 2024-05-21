package com.example.weatherapp

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.DB.MainDb
import com.example.weatherapp.databinding.ActivityMyQueryBinding


class Requests : AppCompatActivity() {
    lateinit var binding: ActivityMyQueryBinding
    private lateinit var adapter: RequestAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyQueryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.backToWeaterBtn.setOnClickListener {
            val intent = Intent(this@Requests, MainActivity::class.java)
            startActivity(intent)
        }

        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this) {
            val data = mutableListOf("")
            val listView = binding.myListReq
            val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, data)
            listView.adapter = adapter

            it.forEach {
                val text =
                    "  ${it.id}. ${it.city} Last update: ${it.time} Condition: ${it.condition} Current Temp.: ${it.currentTemp} Min Temp.: ${it.minTemp} Max Temp.: ${it.maxTemp} "
                data.add(text)


            }

        }

    }
}

