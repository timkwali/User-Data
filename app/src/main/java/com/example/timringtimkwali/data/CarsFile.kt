package com.example.timringtimkwali.data

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.timringtimkwali.utils.FIRST_LINE
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class CarsFile @Inject constructor(
       val context: Context
) {

    var path = context.getExternalFilesDir(null)
    var carsFile = File(path, "owners/car_ownsers_data.csv")
    private val emptyList = emptyList<List<String>>().toMutableList()

    fun getCarsFile(): MutableList<List<String>> {
        if(carsFile == null) {
            Toast.makeText(context, "No file found. Please provide the correct '.csv' file.", Toast.LENGTH_LONG).show()
            return emptyList
        }
        if(carsFile.extension == "csv") {
            FileInputStream(carsFile).use {
                val bufferedReader = carsFile.bufferedReader()
                var  line = bufferedReader.readLine()
                var list: MutableList<List<String>> = mutableListOf<List<String>>()
                var num = 0

                if(line == FIRST_LINE)  {
                    while(num < 10 && line != null){
                        line = bufferedReader.readLine()
                        val array = line.split(",")
                        list.add(array)
                        num++
                    }
                } else {
                    Toast.makeText(context, "Incorrect file found. Please provide the correct '.csv' file.", Toast.LENGTH_LONG).show()
                }
                return list
            }
        } else {
            Toast.makeText(context, "A '.csv' file is required to continue!", Toast.LENGTH_LONG).show()
        }
        return emptyList
    }
}
