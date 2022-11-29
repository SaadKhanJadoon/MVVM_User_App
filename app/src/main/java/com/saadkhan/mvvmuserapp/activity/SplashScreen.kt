package com.saadkhan.mvvmuserapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.saadkhan.mvvmuserapp.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private var binding: ActivitySplashScreenBinding? = null
    private val splashBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}