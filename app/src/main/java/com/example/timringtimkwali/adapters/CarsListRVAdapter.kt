package com.example.timringtimkwali.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timringtimkwali.databinding.CarsFilterTemplateBinding
import com.example.timringtimkwali.model.CarFilter

class CarsListRVAdapter(private var carsList: List<CarFilter>):
    RecyclerView.Adapter<CarsListRVAdapter.CarsListViewHolder>() {

    inner class CarsListViewHolder(private val binding: CarsFilterTemplateBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: CarFilter) {
            binding.apply {
                carsSerialNumberTv.text = (adapterPosition + 1).toString()
                carsModelValueTv.text = car.carModel
                carsYearValueTv.text = car.year
                carsColorValueTv.text = car.color
                carsCountryValueTv.text = car.country
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsListViewHolder {
        val binding = CarsFilterTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

