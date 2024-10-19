package com.universal.demo.ui.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.universal.demo.data.model.User
import com.universal.demo.databinding.ActivityMainBinding
import com.universal.demo.interfaces.AdapterItemClick
import com.universal.demo.ui.main.adapter.MainAdapter
import com.universal.demo.ui.main.viewmodel.MainViewModel
import com.universal.demo.utils.showViewGone
import com.universal.demo.utils.showViewVisible
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author: Abdul Rehman
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: MainAdapter
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupUI()
        setUpObserver()

        mainViewModel.fetchRemoteUsers()
    }

    private fun setupUI() {
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(object : AdapterItemClick<User> {
            override fun onItemClick(item: User?, position: Int, type: String) {
                Toast.makeText(this@MainActivity, item?.name, Toast.LENGTH_SHORT).show()
            }
        })
        viewBinding.recyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        mainViewModel.userList.observe(this) { listData ->
            listData?.let {
                adapter.submitList(it)
            }
        }
        mainViewModel.loadingState.observe(this) { showProgress ->
            if (showProgress) {
                viewBinding.progressBar.showViewVisible()
            } else {
                viewBinding.progressBar.showViewGone()
            }
        }
        mainViewModel.errorState.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}