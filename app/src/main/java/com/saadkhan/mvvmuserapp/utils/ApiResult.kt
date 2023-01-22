package com.saadkhan.mvvmuserapp.utils

import com.saadkhan.mvvmuserapp.model.UserModel

sealed class ApiResult {
    class Success(val userModel: UserModel) : ApiResult()
    object Error : ApiResult()
    object Loading : ApiResult()
}