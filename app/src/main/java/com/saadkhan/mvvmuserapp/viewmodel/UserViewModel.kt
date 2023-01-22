package com.saadkhan.mvvmuserapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saadkhan.mvvmuserapp.repository.UserRepository
import com.saadkhan.mvvmuserapp.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _apiResult = MutableLiveData<ApiResult>()
    val apiResult: MutableLiveData<ApiResult> = _apiResult

    init {
        getAllUser()
    }

    private fun getAllUser() = viewModelScope.launch {
        _apiResult.value = ApiResult.Loading
        try {
            val data = repository.getAllUser()
            _apiResult.value = ApiResult.Success(data)
        } catch (e: Exception) {
            _apiResult.value = ApiResult.Error
        }
    }
}



