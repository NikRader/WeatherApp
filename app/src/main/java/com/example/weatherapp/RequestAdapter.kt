package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.RequestItemBinding
import com.squareup.picasso.Picasso

class RequestAdapter : ListAdapter<WeatherModel, RequestAdapter.MyHolder>(ComparatorReq()){

    var data: List<WeatherModel> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }
    class MyHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = RequestItemBinding.bind(view)

        fun bind(item: WeatherModel) =  with(binding){
            cityReq.text = item.city
            dateReq.text = item.time
            conditionReq.text = item.condition
            tempReq.text = item.currentTemp
            minTempReq.text = item.minTemp
            maxTempReq.text = item.maxTemp
            Picasso.get().load("https:" + item.imageUrl).into(imReq)
           // To Do add data in item_card (в разметку!)
        }
    }

    class ComparatorReq : DiffUtil.ItemCallback<WeatherModel>() {
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.request_item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       holder.bind(getItem(position))
    }
}