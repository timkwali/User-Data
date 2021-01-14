package com.example.timringtimkwali.ui.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : Fragment() {

    private var _binding: FragmentCarsBinding? = null
    private val binding get() = _binding
    private lateinit var carsAdapter: CarsListRVAdapter
    private lateinit var ownersAdapter: CarOwnersListRVAdapter
    private lateinit var genderAdapter: GenderListRVAdapter
    private lateinit var fileList: MutableList<List<String>>
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

        fileList = CarsFile(requireContext()).getCarsFile()
        getCarFilterList()
        getCarsOwnerFilterList()
        getGenderFilterList("male")

        carsAdapter = CarsListRVAdapter(carFilterList)
        ownersAdapter = CarOwnersListRVAdapter(carsOwnerFilterList)
        genderAdapter = GenderListRVAdapter(genderFilterList)

        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = genderAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }

    private fun getCarFilterList() {
        val list = mutableListOf<CarFilter>()
        for(index in fileList.indices){
            list.add( CarFilter(fileList[index][5], fileList[index][6], fileList[index][7], fileList[index][4]) )
        }
        carFilterList = list
    }

    private fun getCarsOwnerFilterList() {
        val list = mutableListOf<CarOwnerFilter>()
        for(index in fileList.indices){
            list.add( CarOwnerFilter(fileList[index][1] + " " + fileList[index][2], fileList[index][5],
                    fileList[index][8], fileList[index][3], fileList[index][9], fileList[index][10]) )
        }
        carsOwnerFilterList = list
    }

    private fun getGenderFilterList(gender: String) {
        val list = mutableListOf<GenderFilter>()
        for(index in fileList.indices) {
            if(fileList[index][8].toLowerCase() == gender) {
                list.add( GenderFilter(fileList[index][1] + " " + fileList[index][2], fileList[index][8],
                        fileList[index][5], fileList[index][7]) )
            }
        }
        genderFilterList = list
    }
}