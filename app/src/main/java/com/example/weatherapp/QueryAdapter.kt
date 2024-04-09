package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class QueryAdapter : ListAdapter<QueryModel, QueryAdapter.MyHolder>(Comparator()) {
    class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)

        // ------------Заполнение item-----------------------
        fun bind(item: QueryModel) = with(binding) {

            // --------------TO DO-------------------------------

        }


    }

    // Класс Comparator проверяет, есть ли изменения в новом списке
    // Нужно ли перерисовать
    class Comparator : DiffUtil.ItemCallback<QueryModel>() {


        override fun areItemsTheSame(oldItem: QueryModel, newItem: QueryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: QueryModel, newItem: QueryModel): Boolean {
            return oldItem == newItem
        }

    }

    // Cоздание view-элемента
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.query_item, parent, false)
        return MyHolder(view)
    }

    // Заполнение элемента
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.bind(getItem(position))
        }


}
