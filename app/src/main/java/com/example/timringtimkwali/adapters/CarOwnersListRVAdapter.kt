package com.example.timringtimkwali.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.timringtimkwali.databinding.CarOwnerFilterTemplateBinding
import com.example.timringtimkwali.databinding.CarsFilterTemplateBinding
import com.example.timringtimkwali.model.CarFilter
import com.example.timringtimkwali.model.CarOwnerFilter

class CarOwnersListRVAdapter (private var ownersList: List<CarOwnerFilter>):
        RecyclerView.Adapter<CarOwnersListRVAdapter.CarOwnersViewHolder>() {

    inner class CarOwnersViewHolder(private val binding: CarOwnerFilterTemplateBinding):
            RecyclerView.ViewHolder(binding.root) {

        fun bind(ownerFilter: CarOwnerFilter) {
            binding.apply {
                ownerSerialNumberTv.text = (adapterPosition + 1).toString()
                ownerOwnerValueTv.text = ownerFilter.owner
                ownerModelValueTv.text = ownerFilter.carModel
                genderGenderValueTv.text = ownerFilter.gender
                ownerEmailValueTv.text = ownerFilter.email
                ownerJobValueTv.text = ownerFilter.jobTitle
                ownerBioValueTv.text = ownerFilter.bio
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarOwnersViewHolder {
        val binding = CarOwnerFilterTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarOwnersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarOwnersViewHolder, position: Int) {
        val currentOwner = ownersList[position]
        holder.bind(currentOwner)
    }

    override fun getItemCount(): Int {
        return ownersList.size
    }
}