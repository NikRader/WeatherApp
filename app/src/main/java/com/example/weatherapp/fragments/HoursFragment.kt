package com.example.weatherapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.databinding.FragmentHoursBinding
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject


class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    // Переменная для адаптера
    private lateinit var adapter: WeatherAdapter
    private val model : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveDataCurrent.observe(viewLifecycleOwner){
        adapter.submitList(getHoursList(it))


        }
    }

    private fun initRcView() = with(binding){
        // Разметка для RcView может быть разной, как вертикальной,
        // Так и горизонатальной.  LinearLayoutManager по умолчанию создает вертик.
        hoursRecycler.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        hoursRecycler.adapter = adapter
    }

    private fun getHoursList(wItem: WeatherModel): List<WeatherModel>{
         // Переводим строку hours в json-массив
        val hourArray = JSONArray(wItem.hours)
        val list = ArrayList<WeatherModel>()
        for (i in 0 until hourArray.length()){

            val item = WeatherModel(
                wItem.city,
                (hourArray[i] as JSONObject).getString("time"),
                (hourArray[i] as JSONObject).getJSONObject("condition")
                    .getString("text"),
                (hourArray[i] as JSONObject).getString("temp_c"),
                "",
                "",
                (hourArray[i] as JSONObject).getJSONObject("condition")
                    .getString("icon"),
                ""
            )
            list.add(item)
        }
        return list
    }
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}