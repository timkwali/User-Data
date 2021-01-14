package com.example.timringtimkwali.viewmodel

import android.app.Activity
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.timringtimkwali.data.CarsFile
import com.example.timringtimkwali.model.CarFilter
import com.example.timringtimkwali.model.CarOwnerFilter
import com.example.timringtimkwali.model.GenderFilter
import kotlinx.coroutines.launch

class CarsViewModel(
        context: Context,
        filter: String
): ViewModel() {

    private lateinit var fileList: MutableList<List<String>>
    lateinit var carsList: List<CarFilter>
    lateinit var ownersList: List<CarOwnerFilter>
    lateinit var genderList: List<GenderFilter>

    init {
        viewModelScope.launch {
            /** ALL LIST */
            fileList = CarsFile(context).getCarsFile()

            /** MODIFY LIST */
            when(filter) {
                "cars" -> getCarFilterList()
                "owners" -> getCarsOwnerFilterList()
                "male" -> getGenderFilterList("male")
                "female" -> getGenderFilterList("female")
            }
        }
    }

    private fun getCarFilterList() {
        val list = mutableListOf<CarFilter>()
        for(index in fileList.indices){
            list.add( CarFilter(fileList[index][5], fileList[index][6], fileList[index][7], fileList[index][4]) )
        }
        carsList = list
    }

    private fun getCarsOwnerFilterList() {
        val list = mutableListOf<CarOwnerFilter>()
        for(index in fileList.indices){
            list.add( CarOwnerFilter(fileList[index][1] + " " + fileList[index][2], fileList[index][5],
                    fileList[index][8], fileList[index][3], fileList[index][9], fileList[index][10]) )
        }
        ownersList = list
    }

    private fun getGenderFilterList(gender: String) {
        val list = mutableListOf<GenderFilter>()
        for(index in fileList.indices) {
            if(fileList[index][8].toLowerCase() == gender) {
                list.add( GenderFilter(fileList[index][1] + " " + fileList[index][2], fileList[index][8],
                        fileList[index][5], fileList[index][7]) )
            }
        }
        genderList = list
    }
}