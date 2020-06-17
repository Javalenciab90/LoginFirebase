package com.java90.firebasetesting.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.java90.firebasetesting.R
import com.java90.firebasetesting.data.network.AuthRepoImp
import com.java90.firebasetesting.domain.usecases.AuthUseCases

class AuthenticationActivity : AppCompatActivity() {

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        val repository = AuthRepoImp()
        val viewModelFactory = AuthUseCases(repository)
        viewModel = ViewModelProvider(this,AuthViewModelFactory(viewModelFactory))
            .get(AuthViewModel::class.java)
    }
}
