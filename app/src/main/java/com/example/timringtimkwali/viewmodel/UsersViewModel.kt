package com.example.timringtimkwali.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.timringtimkwali.databinding.FragmentUsersBinding
import com.example.timringtimkwali.model.User
import com.example.timringtimkwali.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel @ViewModelInject constructor(
    var usersApi: ApiService,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _allUsers = MutableLiveData<List<User>>()
    val allUsers: LiveData<List<User>> get() = _allUsers

    private var _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        val users = usersApi.api.getAllUsers()
        users.enqueue(
            object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    _result.value = "failure"
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    _result.value = "Success"
                    _allUsers.value = response.body()
                    Log.d("SUCCESS", _allUsers.toString())
                }
            }
        )
    }

}
