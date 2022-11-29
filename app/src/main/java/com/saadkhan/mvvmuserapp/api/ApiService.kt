package com.saadkhan.mvvmuserapp.api

import com.saadkhan.mvvmuserapp.model.UserModel
import com.saadkhan.mvvmuserapp.utils.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.BASE_URL)
    suspend fun getAllUsers(
    ): List<UserModel>

}