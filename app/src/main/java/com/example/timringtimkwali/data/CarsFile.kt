package com.example.timringtimkwali.data

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.timringtimkwali.utils.DownloadFile
import com.example.timringtimkwali.utils.FILE_URL
import com.example.timringtimkwali.utils.FIRST_LINE
import java.io.File
import java.io.FileInputStream
import java.net.URL
import javax.inject.Inject

class CarsFile @Inject constructor(
       val context: Context
) {
    val path = File(context.filesDir, "owners${File.separator}")
    private var carsFile = File(path, "car_ownsers_data.csv")
    private val emptyList = emptyList<List<String>>().toMutableList()

    fun getCarsFile(): MutableList<List<String>> {

        if(carsFile.extension == "csv") {
            FileInputStream(carsFile).use {
                val bufferedReader = carsFile.bufferedReader()
                var  line = bufferedReader.readLine()
                var list: MutableList<List<String>> = mutableListOf<List<String>>()
                var num = 1
                if(line == FIRST_LINE)  {
                    while(line != null){
                        if(num > 0) {
                            line = bufferedReader.readLine()
                            num--
                        }
                        val array = line.split(",")
                        list.add(array)
                        line = bufferedReader.readLine()
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
