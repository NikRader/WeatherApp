package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.WeatherAdapter
import com.example.weatherapp.databinding.FragmentQueryBinding

class QueryFragment : Fragment() {

    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: FragmentQueryBinding
    private val model: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQueryBinding.inflate(inflater,  container, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =QueryFragment()

            }
}
