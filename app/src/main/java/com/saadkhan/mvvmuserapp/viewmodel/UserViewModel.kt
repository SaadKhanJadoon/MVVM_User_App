package com.saadkhan.mvvmuserapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saadkhan.mvvmuserapp.model.UserModel
import com.saadkhan.mvvmuserapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    var allUser = MutableLiveData<ArrayList<UserModel>>()

    fun getAllUser() = viewModelScope.launch {
        allUser.value = repository.getAllUser() as ArrayList<UserModel>
    }
}