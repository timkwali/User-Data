package com.example.timringtimkwali.data

import com.example.timringtimkwali.model.User
import com.google.gson.annotations.SerializedName

//data class UsersResponse (val list: List<User>)

data class UsersResponse (
    @SerializedName("data")
    val `data`: List<User>
)