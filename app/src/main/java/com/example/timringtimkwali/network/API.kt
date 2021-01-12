package com.example.timringtimkwali.network

import com.example.timringtimkwali.data.ApiResponse
import com.example.timringtimkwali.data.UsersResponse
import com.example.timringtimkwali.model.User
import retrofit2.Call
import retrofit2.http.GET

interface API {

    /** GET USERS DATA */
    @GET("accounts")
    fun getAllUsers(): Call<List<User>>
}