package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.DB.MainDb
import com.example.weatherapp.databinding.ActivityMyQueryBinding


class MyQuery : AppCompatActivity() {
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
           // val list_req =
                binding.tvList.text = ""
                it.forEach {
                val text =
                        "  ${it.id}. ${it.city} Last update: ${it.time} Condition: ${it.condition} Current Temp.: ${it.currentTemp} Min Temp.: ${it.minTemp} Max Temp.: ${it.maxTemp} \n-------------------------------------------------------------------------------------\n"
                binding.tvList.append(text)

                }
            }
        }

    private fun initRcView() = with(binding){
        // Разметка для RcView может быть разной, как вертикальной,
        // Так и горизонатальной.  LinearLayoutManager по умолчанию создает вертик.
        reqRcView.layoutManager = LinearLayoutManager(this@MyQuery)
        adapter = RequestAdapter()
        reqRcView.adapter = adapter
    }

    private fun init_btn(){
        binding.backToWeaterBtn.setOnClickListener {
            val intent = Intent(this@MyQuery, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
