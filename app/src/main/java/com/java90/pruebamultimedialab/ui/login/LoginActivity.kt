package com.java90.pruebamultimedialab.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.java90.pruebamultimedialab.BaseActivity
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.data.network.LoginRepoImpl
import com.java90.pruebamultimedialab.ui.main.MainActivity
import com.java90.pruebamultimedialab.ui.register.RegisterActivity
import com.java90.pruebamultimedialab.ui.reset.ResetActivity
import com.java90.pruebamultimedialab.usecases.LoginUseCase
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private val loginViewModel by lazy {
        ViewModelProvider(this,
            LoginViewModelFactory(LoginUseCase(LoginRepoImpl())))
            .get(LoginViewModel::class.java)
    }

    override fun getViewID(): Int = R.layout.activity_login
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        textView_go_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        textView_forgot_pass.setOnClickListener {
            val intent = Intent(this, ResetActivity::class.java)
            startActivity(intent)
        }

        button_login.setOnClickListener {
            signIn(editText_email_login.text.toString().trim(),
                editText_password_login.text.toString().trim())
        }
    }

    private fun signIn(email: String, password: String) {

        if(!validateForm(email, password)) {
            return
        }

        loginViewModel.signIn(email, password).observe(this,
            Observer { result ->
                when(result) {
                    is Resource.Loading -> {
                        textView_forgot_pass.visibility = View.INVISIBLE
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        showToast("Inicio de sesión exitoso")
                        hideProgressBar()
                        textView_forgot_pass.visibility = View.VISIBLE
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    is Resource.Failure -> {
                        errorMessageFirebase(result.exception.message!!)
                        hideProgressBar()
                        textView_forgot_pass.visibility = View.VISIBLE
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
