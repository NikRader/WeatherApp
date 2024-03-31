package com.example.weatherapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.fragments.DaysFragment
import com.example.weatherapp.fragments.HoursFragment
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResult("London")
        days_hours_btns()
    }
    private fun days_hours_btns(){
        binding.daysBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, DaysFragment.newInstance()).commit()
        }
        binding.hoursBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.placeHolder, HoursFragment.newInstance()).commit()
        }


    }
    private fun getResult(city: String){
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$city&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(com.android.volley.Request.Method.GET,
            url,
            {               // Для получения ответных данных использую функцию parseWeatherData(result: String)
                result -> parseWeatherData(result)
            },
            {
              error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(stringRequest)
    }
    private fun parseWeatherData(result: String){
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        // list[0] - это значит сегодняшний день
        parseCurrentData(mainObject, list[0])
    }

    private fun parseDays(mainObject: JSONObject):List<WeatherModel> {
        val list = ArrayList<WeatherModel>()
        val daysArray = mainObject.getJSONObject("forecast")
            .getJSONArray("forecast")
        val name = mainObject.getJSONObject("location").getString("name")
        for (i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition")
                    .getString("icon"),
                // Информацию по часам просто записали строкой
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        return list
    }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem:WeatherModel){
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            weatherItem.hours
        )
        Log.d("MyLog", "MaxTemp: ${item.maxTemp}")
        Log.d("MyLog", "MinTemp: ${item.minTemp}")
        Log.d("MyLog", "Hours: ${item.hours}")

    }
}



