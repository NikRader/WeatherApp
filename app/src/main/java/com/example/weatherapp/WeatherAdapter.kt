package com.example.weatherapp

import android.view.LayoutInflater
import androidx.recyclerview.widget.ListAdapter
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso


const val API_KEY ="b493d3eeff724b3f9a954927242303"
class WeatherAdapter : ListAdapter<WeatherModel, WeatherAdapter.Holder>(Comparator()) {
        // Сохранение шаблона, который мы создали
    class Holder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ListItemBinding.bind(view)

        fun bind(item: WeatherModel) = with(binding){
            tvDate.text = item.time
            tvCondition.text = item.condition
           // tvTemp.text = item.currentTemp.ifEmpty { "${item.minTemp } °С/ ${item.maxTemp } °С" }
            tvTemp.text = item.currentTemp.ifEmpty { "${item.minTemp } / ${item.maxTemp } " }
            Picasso.get().load("https:" + item.imageUrl).into(im)
        }


    }
    // Класс Comparator проверяет, есть ли изменения в новом списке
    // Нужно ли перерисовать
    class Comparator : DiffUtil.ItemCallback<WeatherModel>(){
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

    }
        // Cоздание view-элемента
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent,false)
            return Holder(view)
    }
        // Заполнение элемента
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
