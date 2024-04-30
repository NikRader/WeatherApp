package com.example.weatherapp.fragments
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.DB.Item
import com.example.weatherapp.DB.MainDb
import com.example.weatherapp.DialogManager
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.MyQuery
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject


const val API_KEY = "b493d3eeff724b3f9a954927242303"

class MainFragment : Fragment()  {

    private lateinit var binding: FragmentMainBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateCurrentCard()
        requestWeatherData("Nizhny Novgorod")
        binding.searchBtn.setOnClickListener {
            DialogManager.searchByNameDialog(requireContext(), object: DialogManager.Listener{
                override fun onClick(name: String?) {
                    // Передача параметра name в функцию запроса
                    name?.let { it1 -> requestWeatherData(it1) }
                }
            } )
        }
    }

    private fun requestWeatherData(city: String){
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "3" +     // максимальное кол-во дней сайтом предоставляется: 3
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> parseWeatherData(result)
                    Log.d("MyLog", "Data was got!")
            },
            {
                    error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    private fun parseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])
    }

    private fun parseDays(mainObject: JSONObject): List<WeatherModel>{
        val list = ArrayList<WeatherModel>()
        val daysArray = mainObject.getJSONObject("forecast")
            .getJSONArray("forecastday")
        val name =  mainObject.getJSONObject("location").getString("name")
        for (i in 0 until daysArray.length()){
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
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        // Передаем данные во ViewModel
        model.liveDataList.value = list
        return list
    }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel){
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current")
                .getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            mainObject.getJSONObject("current")
                .getJSONObject("condition").getString("icon"),
            weatherItem.hours
        )

        // Перезаписываем WeatherModel
        model.liveDataCurrent.value = item

        // ------Делаем запись в БД------------------
        val db = MainDb.getDb(this)
        val itembd = Item(null,"","","","","","" )
        itembd.city        = item.city
        itembd.time        = item.time
        itembd.condition   = item.condition
        itembd.currentTemp = item.currentTemp
        itembd.minTemp     = item.minTemp
        itembd.maxTemp     = item.maxTemp

        Thread{
            db.getDao().insertItem(itembd)
        }.start()

    }
        //  Функция для обновления Текущей погоды (CurrentCard)
    private fun updateCurrentCard() = with(binding){
        // Следит за обновлениями фрагмента(функция observe - ждет данные)
        model.liveDataCurrent.observe(viewLifecycleOwner){
           val maxMinTemp = "${it.minTemp}°C / ${it.maxTemp}°C"
            tvData.text = it.time
            tvCity.text = it.city
            tvCurrentTemp.text = "${it.currentTemp} °C"
            tvCondition.text = it.condition
            tvMinMax.text = maxMinTemp
            // Picasso скачает картинку и добавит в ImageView
           Picasso.get().load("https:" + it.imageUrl).into(weatherImage)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}