package com.example.timringtimkwali.network

import com.example.timringtimkwali.data.ApiResponse
import com.example.timringtimkwali.data.UsersResponse
import com.example.timringtimkwali.model.User
import retrofit2.Call
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: API
): UsersRepository{

    override suspend fun getAllUsers(): Call<List<User>> {
        return api.getAllUsers()
    }
}