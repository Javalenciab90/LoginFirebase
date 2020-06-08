package com.java90.pruebamultimedialab.ui.auth.register

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.data.network.RegisterRepoImp
import com.java90.pruebamultimedialab.domain.usecases.RegisterUseCase
import com.java90.pruebamultimedialab.ui.main.BaseFragment
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun getViewID(): Int = R.layout.fragment_register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = RegisterRepoImp()
        val viewModelFactory = RegisterUseCase(repository)
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(viewModelFactory))
            .get(RegisterViewModel::class.java)

        btn_signUp.setOnClickListener {
            signUpUser(etEmailRegister.text.toString().trim(),
                       etPasswordRegister.text.toString().trim(),
                       etPassConfirmed.text.toString().trim())
        }

        tv_btn_goLogin.setOnClickListener {
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun signUpUser(email: String, password: String, passConfirmed: String) {

        if(!validateForm(email, password, passConfirmed)) return

        viewModel.registerUser(email, password).observe(viewLifecycleOwner,
            Observer { result->
                when(result) {
                    is Resource.Loading -> {
                        btn_signUp.visibility = View.GONE
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        btn_signUp.visibility = View.VISIBLE
                        hideProgressBar()
                        Log.d("TAG", result.data.toString())
                    }
                    is Resource.Failure -> {
                        btn_signUp.visibility = View.VISIBLE
                        hideProgressBar()
                        errorMessageFirebase(result.toString())
                    }
                }
            }
        )
    }

    private fun validateForm(email: String, password: String, confirmPass: String) : Boolean{

        if(TextUtils.isEmpty(email)) {
            showToast("Ingresar email válido!")
            return false
        }
        if (TextUtils.isEmpty(password)) {
            showToast("Ingresar contraseña!")
            return false
        }
        if(password.length < 6) {
            showToast("Contraseña muy corta, ingrese mínimo 6 caracteres!")
            return false
        }
        if(password != confirmPass) {
            showToast("Conraseñas no coinciden. Verifique!")
            return false
        }
        return true
    }

    private fun showProgressBar() {
       registerProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        registerProgressBar.visibility = View.GONE
    }

}