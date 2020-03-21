package com.java90.pruebamultimedialab.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.java90.pruebamultimedialab.BaseActivity
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.data.network.RegisterRepoImpl
import com.java90.pruebamultimedialab.ui.login.LoginActivity
import com.java90.pruebamultimedialab.usecases.RegisterUseCase
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    private val  registerViewModel by lazy {
        ViewModelProvider(this,
            RegisterViewModelFactory(RegisterUseCase(RegisterRepoImpl())))
            .get(RegisterViewModel::class.java)
    }

    override fun getViewID(): Int = R.layout.activity_register
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView_go_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        button_register.setOnClickListener {
            signUp(editText_email_login.text.toString().trim(),
                editText_password_login.text.toString().trim(),
                editText_confirm_pass.text.toString().trim()
            )
        }

    }

    private fun signUp(email: String, password: String, confirmPass: String) {

        if (!validateForm(email, password, confirmPass)) {
            return
        }

        registerViewModel.signUp(email, password).observe(this,
            Observer { result ->
                when(result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        hideProgressBar()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    is Resource.Failure -> {
                        errorMessageFirebase(result.exception.message!!)
                        hideProgressBar()
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
