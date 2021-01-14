package com.example.timringtimkwali.data

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.example.timringtimkwali.utils.FIRST_LINE
import java.io.File
import java.io.FileInputStream

class CarsFile(var context: Context) {

    var path = context.getExternalFilesDir(null)
    var carsFile = File(path, "owners/car_ownsers_data.csv")

    fun getCarsFile(): MutableList<List<String>> {
        FileInputStream(carsFile).use {
            val bufferedReader = carsFile.bufferedReader()
            var  line = bufferedReader.readLine()
            var list: MutableList<List<String>> = mutableListOf<List<String>>()
            var num = 0

            while(num < 2 && line != null){
                line = bufferedReader.readLine()
                val array = line.split(",")
                list.add(array)
                num++
            }
//            println(list)
//            Toast.makeText(context, list.toString(), Toast.LENGTH_LONG).show()
            return list

//            while(line != null) {
////                    println(line)
////                    Log.d("FILE", line)
//                line = bufferedReader.readLine()
//                val array = line.split(",")
//                Log.d("ARRAY", array.toString())
//                println(array)
//            }

//            if(line == FIRST_LINE)  {
//            } else {
//                Toast.makeText(context, "Incorrect file found, please provide the correct one.", Toast.LENGTH_LONG).show()
//            }
        }
    }
}
