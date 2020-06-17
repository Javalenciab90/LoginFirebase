package com.java90.firebasetesting.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.java90.firebasetesting.R
import com.java90.firebasetesting.data.network.MainRepoImp
import com.java90.firebasetesting.domain.usecases.MainUseCases
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MainRepoImp()
        val viewModelFactory = MainUseCases(repository)
        viewModel = ViewModelProvider(this, MainViewModelFactory(viewModelFactory))
            .get(MainViewModel::class.java)

        bottomNavigationView.setupWithNavController(nav_graph_host_main.findNavController())
    }
}