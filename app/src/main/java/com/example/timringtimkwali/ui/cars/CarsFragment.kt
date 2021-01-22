package com.example.timringtimkwali.ui.cars

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timringtimkwali.R
import com.example.timringtimkwali.adapters.CarOwnersListRVAdapter
import com.example.timringtimkwali.adapters.CarsListRVAdapter
import com.example.timringtimkwali.adapters.GenderListRVAdapter
import com.example.timringtimkwali.databinding.FragmentCarsBinding
import com.example.timringtimkwali.model.CarFilter
import com.example.timringtimkwali.model.CarOwnerFilter
import com.example.timringtimkwali.model.GenderFilter
import com.example.timringtimkwali.utils.DownloadFile
import com.example.timringtimkwali.utils.FILE_URL
import com.example.timringtimkwali.viewmodel.CarsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class CarsFragment : Fragment() {

    private var _binding: FragmentCarsBinding? = null
    private val binding get() = _binding
    private lateinit var carsViewModel: CarsViewModel
    private var filter: String = "cars"
    private lateinit var carsAdapter: CarsListRVAdapter
    private lateinit var ownersAdapter: CarOwnersListRVAdapter
    private lateinit var genderAdapter: GenderListRVAdapter
    private lateinit var carFilterList: List<CarFilter>
    private lateinit var carsOwnerFilterList: List<CarOwnerFilter>
    private lateinit var genderFilterList: List<GenderFilter>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCarsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        carsViewModel = CarsViewModel(requireContext(), filter)
        var file = DownloadFile().readFile(requireContext())
        checkIfFileExists(file)
//        check(file)

        /** SET UP SPINNER */
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.filters,
            R.layout.filter_text
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.carsSelectedFilterSp!!.adapter = adapter
        }
    }

    fun check(file: File) {
        var fl = DownloadFile().readFile(requireContext())
        if(!file.exists()) {
            DownloadFile().downloadCsv(FILE_URL, requireContext())
        }

        val handler = Handler()
        handler.postDelayed({
            while(!fl.exists()) {
                binding?.carsProgressPb?.visibility = View.VISIBLE
                binding?.carsDownloadingTv?.visibility = View.VISIBLE
                binding?.carsSelectedFilterSp?.visibility = View.INVISIBLE
                fl = DownloadFile().readFile(requireContext())
            }
        }, 20000)

        binding?.carsProgressPb?.visibility = View.INVISIBLE
        binding?.carsDownloadingTv?.visibility = View.INVISIBLE
        binding?.carsSelectedFilterSp?.visibility = View.VISIBLE
        Toast.makeText(requireContext(), "downloaded", Toast.LENGTH_SHORT).show()
        setUpList()
    }

    fun checkIfFileExists(file: File) {
        if(!file.exists()) {
            binding?.apply {
                carsProgressPb?.visibility = View.VISIBLE
                carsDownloadingTv?.visibility = View.VISIBLE
                carsSelectedFilterSp?.visibility = View.INVISIBLE
            }
            DownloadFile().downloadCsv(FILE_URL, requireContext())
        } else{
            binding?.carsProgressPb?.visibility = View.INVISIBLE
            binding?.carsDownloadingTv?.visibility = View.INVISIBLE
            binding?.carsSelectedFilterSp?.visibility = View.VISIBLE
            setUpList()
            /** HANDLE FILTER CHANGE */
            binding?.carsSelectedFilterSp!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    setUpFilter()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    private fun setUpList() {
        when(filter) {
            "cars" -> setUpCarsList()
            "owners" -> setUpOwnersList()
            "male" -> setUpGenderList("male")
            "female" -> setUpGenderList("female")
            else -> setUpCarsList()
        }
    }

    private fun setUpFilter() {
        when(binding?.carsSelectedFilterSp!!.selectedItem) {
            "Car" -> filter = "cars"
            "Owner" -> filter = "owners"
            "Gender(Male)" -> filter = "male"
            "Gender(Female)" -> filter = "female"
        }
        setUpList()
    }

    private fun setUpCarsList() {
        carsViewModel = CarsViewModel(requireContext(), "cars")
        carFilterList = carsViewModel.carsList
        carsAdapter = CarsListRVAdapter(carFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = carsAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }

    private fun setUpOwnersList() {
        carsViewModel = CarsViewModel(requireContext(), "owners")
        carsOwnerFilterList = carsViewModel.ownersList
        ownersAdapter = CarOwnersListRVAdapter(carsOwnerFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = ownersAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }

    private fun setUpGenderList(gender: String) {
        carsViewModel = CarsViewModel(requireContext(), gender)
        genderFilterList = carsViewModel.genderList
        genderAdapter = GenderListRVAdapter(genderFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = genderAdapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }
}