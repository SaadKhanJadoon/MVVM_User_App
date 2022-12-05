package com.saadkhan.mvvmuserapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.saadkhan.mvvmuserapp.adapter.UserAdapter
import com.saadkhan.mvvmuserapp.databinding.ActivityMainBinding
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
        loadData()
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

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.allUser.observe(this@MainActivity) {
                userAdapter.submitList(it.results)
                binding?.recyclerView?.unVeil()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}