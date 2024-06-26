package com.example.weatherapp.fragments
import android.os.Bundle
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
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.FragmentDaysBinding


class DaysFragment : Fragment() {
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: FragmentDaysBinding
    private val model: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        model.liveDataList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

    }

    private fun init() = with(binding){
        adapter = WeatherAdapter()
        daysRecycler.layoutManager = LinearLayoutManager(activity)
        daysRecycler.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
}