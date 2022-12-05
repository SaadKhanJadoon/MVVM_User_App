package com.saadkhan.mvvmuserapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.saadkhan.mvvmuserapp.databinding.ActivityUserDetailsBinding
import com.saadkhan.mvvmuserapp.model.User
import com.saadkhan.mvvmuserapp.utils.Companion.Companion.userObject
import com.saadkhan.mvvmuserapp.utils.getUserLocation
import com.saadkhan.mvvmuserapp.utils.getUsername
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {
    private var binding: ActivityUserDetailsBinding? = null
    private val userDetailBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(userDetailBinding.root)
        setData(userObject)
    }

    private fun setData(user: User) {
        binding?.apply {
            tvName.text = user.name?.getUsername() ?: ""
            tvEmail.text = "${user.email}"
            tvPhone.text = "${user.phone}"
            tvGender.text = "${user.gender}"
            tvLocation.text = "${user.location?.getUserLocation()}"

            userImage.load(user.picture?.large) {
                crossfade(true)
                crossfade(500)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}