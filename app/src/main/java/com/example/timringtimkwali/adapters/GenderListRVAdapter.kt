package com.example.timringtimkwali.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timringtimkwali.databinding.CarsFilterTemplateBinding
import com.example.timringtimkwali.databinding.GenderFilterTemplateBinding
import com.example.timringtimkwali.model.CarFilter
import com.example.timringtimkwali.model.GenderFilter

class GenderListRVAdapter (private var genderList: List<GenderFilter>):
        RecyclerView.Adapter<GenderListRVAdapter.GenderListViewHolder>() {

    inner class GenderListViewHolder(private val binding: GenderFilterTemplateBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(gender: GenderFilter) {
            binding.apply {
                genderSerialNumberTv.text = (adapterPosition + 1).toString()
                genderOwnerValueTv.text = gender.owner
                generGenderValueTv.text = gender.gender
                genderModelValueTv.text = gender.carModel
                genderColorValueTv.text = gender.color
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenderListViewHolder {
        val binding = GenderFilterTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenderListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenderListViewHolder, position: Int) {
        val currentGender = genderList[position]
        holder.bind(currentGender)    }

    override fun getItemCount(): Int {
        return genderList.size
    }
}