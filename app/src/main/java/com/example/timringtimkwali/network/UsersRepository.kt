package com.example.timringtimkwali.network

import com.example.timringtimkwali.data.ApiResponse
import com.example.timringtimkwali.data.UsersResponse
import com.example.timringtimkwali.model.User
import retrofit2.Call

interface UsersRepository {
    suspend fun getAllUsers(): Call<List<User>>
}