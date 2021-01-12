package com.example.timringtimkwali.ui.cars

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timringtimkwali.adapters.CarsListRVAdapter
import com.example.timringtimkwali.databinding.FragmentCarsBinding
import com.example.timringtimkwali.model.CarFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsFragment : Fragment() {

    private var _binding: FragmentCarsBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: CarsListRVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCarsBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = CarsListRVAdapter(carFilterList)
        val carsListRV = binding?.carsCarsListRv
        carsListRV?.adapter = adapter
        carsListRV?.layoutManager = LinearLayoutManager(this.context)
        carsListRV?.setHasFixedSize(true)
    }

    private val carsOwnerFilterList = listOf<CarFilter>(

    )

    private val carFilterList = listOf<CarFilter>(
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
        CarFilter("Tesla g6", "2019", "Pink", "Janpan"),
    )

    private val maleFilterList = listOf<CarFilter>()
    private val femaleFilterList = listOf<CarFilter>()
}