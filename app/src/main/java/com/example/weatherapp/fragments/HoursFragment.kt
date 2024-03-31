package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.WeatherModel
import com.example.weatherapp.databinding.FragmentHoursBinding


class HoursFragment : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    // Переменная для адаптера
    private lateinit var adapter: WeatherAdapter
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
    }

    private fun initRcView() = with(binding){
        // Разметка для RcView может быть разной, как вертикальной,
        // Так и горизонатальной.  LinearLayoutManager по умолчанию создает вертик.
        hoursRecycler.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        hoursRecycler.adapter = adapter
        val list = listOf(
            WeatherModel("", "12.00", "Sunny",
                "25","","", "", "" ),

                    WeatherModel("", "13.00", "Warm",
            "20","","", "", "" ),
            WeatherModel("", "14.00", "Rainy",
                "15","","", "", "" )
        )
        // загружаем список
        adapter.submitList(list)
    }
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}