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
        initRcView()
        init_btn()
        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this){
            val data = mutableListOf("")
            val listView = binding.myListReq
            val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, data)
            listView.adapter = adapter
                binding.tvList.text = ""
                it.forEach {
                val text =
                        "  ${it.id}. ${it.city} Last update: ${it.time} Condition: ${it.condition} Current Temp.: ${it.currentTemp} Min Temp.: ${it.minTemp} Max Temp.: ${it.maxTemp} "
                data.add(text)


                }

            }
        }

    private fun initRcView() = with(binding){
//        // Разметка для RcView может быть разной, как вертикальной,
//        // Так и горизонатальной.  LinearLayoutManager по умолчанию создает вертик.
//        reqRcView.layoutManager = LinearLayoutManager(this@Requests)
//        adapter = RequestAdapter()
//        reqRcView.adapter = adapter
//        val list = listOf(
//            WeatherModel(
//                "London","30.04.2024","cloudy", "25.0","30","20","",""
//            ),
//            WeatherModel(
//                "Rome","30.04.2024","sunny", "23.0","28","18","",""
//            ),
//            WeatherModel(
//                "Madrid","30.04.2024","rainy", "21.0","25","16","",""
//            )
//        )
//        adapter.submitList(list)
    }

    private fun init_btn(){
        binding.backToWeaterBtn.setOnClickListener {
            val intent = Intent(this@Requests, MainActivity::class.java)
            startActivity(intent)
        }
        binding.deleteBtn.setOnClickListener {
            val db = MainDb.getDb(this)
            db.getDao().deleteAllItem()
        }
    }
}
