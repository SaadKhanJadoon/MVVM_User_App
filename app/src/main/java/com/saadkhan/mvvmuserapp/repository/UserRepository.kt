package com.saadkhan.mvvmuserapp.repository

import com.saadkhan.mvvmuserapp.api.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private var apiService: ApiService) {

    suspend fun getAllUser() = apiService.getAllUsers()
}