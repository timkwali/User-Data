package com.example.timringtimkwali.network

import retrofit2.Retrofit
import javax.inject.Inject

class ApiService @Inject constructor(retrofit: Retrofit) {
    val api: API = retrofit.create(API::class.java)
}