package com.saadkhan.mvvmuserapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.saadkhan.mvvmuserapp.adapter.UserAdapter
import com.saadkhan.mvvmuserapp.databinding.ActivityMainBinding
import com.saadkhan.mvvmuserapp.model.UserModel
import com.saadkhan.mvvmuserapp.utils.ApiResult
import com.saadkhan.mvvmuserapp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val mainBinding get() = binding!!
    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        setupRecyclerView()
        loadDataFromViewModel()
    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.apply {
            setAdapter(userAdapter, LinearLayoutManager(this@MainActivity))
            addVeiledItems(10)

            userAdapter.setOnItemClickListener(object : UserAdapter.OnItemClickListener {
                override fun onItemClick() {
                    val mainIntent = Intent(this@MainActivity, UserDetailsActivity::class.java)
                    startActivity(mainIntent)
                }
            })
        }
    }

    private fun loadDataFromViewModel() {
        lifecycleScope.launch {
            viewModel.apiResult.observe(this@MainActivity) {
                when (it) {
                    is ApiResult.Success -> showData(it.userModel)
                    is ApiResult.Error -> showError()
                    is ApiResult.Loading -> showLoading()
                }
            }
        }
    }

    private fun showData(userModel: UserModel) {
        userAdapter.submitList(userModel.results)
        binding?.recyclerView?.unVeil()
    }

    private fun showError() {
        Snackbar.make(binding?.root!!, "Error in loading data", Snackbar.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding?.recyclerView?.veil()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}