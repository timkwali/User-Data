package com.example.timringtimkwali.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timringtimkwali.databinding.CarsListTemplateBinding
import com.example.timringtimkwali.model.Car

class CarsListRVAdapter(private var carsList: List<Car>):
    RecyclerView.Adapter<CarsListRVAdapter.CarsListViewHolder>() {

    inner class CarsListViewHolder(private val binding: CarsListTemplateBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) {
            binding.apply {
                carsCarDetailsTv.text = car.carDetails
                carsSerialNumberTv.text = (adapterPosition + 1).toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsListViewHolder {
        val binding = CarsListTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsListViewHolder, position: Int) {
        val currentCar = carsList[position]
        holder.bind(currentCar)
    }

    override fun getItemCount(): Int {
        return carsList.size
    }
}

