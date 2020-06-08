package com.java90.pruebamultimedialab.ui.auth.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.data.network.LoginRepoImp
import com.java90.pruebamultimedialab.domain.usecases.LoginUseCase
import com.java90.pruebamultimedialab.ui.main.BaseFragment
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    private val viewModel by lazy {
        val repository = LoginRepoImp()
        val viewModelFactory = LoginUseCase(repository)
        ViewModelProvider(this, LoginViewModelFactory(viewModelFactory))
            .get(LoginViewModel::class.java)
    }

    override fun getViewID(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_signIn.setOnClickListener {
            singInUser(etEmailLogin.text.toString().trim(), etPasswordLogin.text.toString().trim())
        }

        tv_btn_go_register.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        tvForgotPass.setOnClickListener {
            view.findNavController().navigate(R.id.action_loginFragment_to_resetFragment)
        }
    }


    private fun singInUser(email: String, password: String) {
        if(!validateForm(email, password)) return

        viewModel.loginUser(email, password).observe(viewLifecycleOwner,
            Observer { result ->
                when(result) {
                    is Resource.Loading -> {
                        btn_signIn.visibility = View.GONE
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        btn_signIn.visibility = View.VISIBLE
                        hideProgressBar()
                        showToast(result.data.toString())
                    }
                    is Resource.Failure -> {
                        btn_signIn.visibility = View.VISIBLE
                        hideProgressBar()
                        errorMessageFirebase(result.data.toString())
                    }
                }
            }
        )
    }

    private fun validateForm(email: String, password: String) : Boolean{

        if(TextUtils.isEmpty(email)) {
            showToast("Ingresar email válido!")
            return false
        }
        if (TextUtils.isEmpty(password)) {
            showToast("Ingresar contraseña!")
            return false
        }
        return true
    }

    private fun showProgressBar() {
        loginProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        loginProgressBar.visibility = View.GONE
    }
}