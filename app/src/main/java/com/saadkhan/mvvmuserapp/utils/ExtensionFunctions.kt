package com.saadkhan.mvvmuserapp.utils

import com.saadkhan.mvvmuserapp.model.Name

fun Name.getUsername(): String {
    return "${this?.title + " " + (this?.first) + " " + (this?.last)}"
}