package com.saadkhan.mvvmuserapp.utils

import com.saadkhan.mvvmuserapp.model.Location
import com.saadkhan.mvvmuserapp.model.Name

fun Name.getUsername(): String {
    return "${this?.title + " " + (this?.first) + " " + (this?.last)}"
}

fun Location.getUserLocation(): String {
    return "${this?.street?.number.toString() + " " + this?.street?.name + " " + (this?.city) + " " + (this?.state) + " " + (this?.country)}"
}