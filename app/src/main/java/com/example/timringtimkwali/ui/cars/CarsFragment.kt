package com.example.timringtimkwali.ui.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timringtimkwali.adapters.CarOwnersListRVAdapter
import com.example.timringtimkwali.adapters.CarsListRVAdapter
import com.example.timringtimkwali.adapters.GenderListRVAdapter
import com.example.timringtimkwali.data.CarsFile
import com.example.timringtimkwali.databinding.FragmentCarsBinding
import com.example.timringtimkwali.model.CarFilter
import com.example.timringtimkwali.model.CarOwnerFilter
import com.example.timringtimkwali.model.GenderFilter
import com.example.timringtimkwali.viewmodel.CarsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : Fragment() {

    private var _binding: FragmentCarsBinding? = null
    private val binding get() = _binding
    private lateinit var carsViewModel: CarsViewModel
    private var filter: String = "owners"
    private lateinit var carsAdapter: CarsListRVAdapter
    private lateinit var ownersAdapter: CarOwnersListRVAdapter
    private lateinit var genderAdapter: GenderListRVAdapter
    private lateinit var carFilterList: List<CarFilter>
    private lateinit var carsOwnerFilterList: List<CarOwnerFilter>
    private lateinit var genderFilterList: List<GenderFilter>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCarsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        carsViewModel = CarsViewModel(requireContext(), filter)
        setUpList()

        /** CHANGE LIST FILTER */

    }

    private fun setUpList() {
        when(filter) {
            "cars" -> setUpCarsList()
            "owners" -> setUpOwnersList()
            "male" -> setUpGenderList()
            "female" -> setUpGenderList()
            else -> setUpCarsList()
        }
    }

    private fun setUpCarsList() {
        carFilterList = carsViewModel.carsList
        carsAdapter = CarsListRVAdapter(carFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = carsAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }

    private fun setUpOwnersList() {
        carsOwnerFilterList = carsViewModel.ownersList
        ownersAdapter = CarOwnersListRVAdapter(carsOwnerFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = ownersAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }

    private fun setUpGenderList() {
        genderFilterList = carsViewModel.genderList
        genderAdapter = GenderListRVAdapter(genderFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = genderAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }
}