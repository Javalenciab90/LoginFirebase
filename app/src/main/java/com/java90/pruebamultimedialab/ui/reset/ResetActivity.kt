package com.java90.pruebamultimedialab.ui.reset

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.java90.pruebamultimedialab.BaseActivity
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.data.network.ResetRepoImpl
import com.java90.pruebamultimedialab.ui.login.LoginActivity
import com.java90.pruebamultimedialab.usecases.ResetUseCase
import com.java90.pruebamultimedialab.vo.Resource
import kotlinx.android.synthetic.main.activity_reset.*

class ResetActivity : BaseActivity() {

    private val resetViewModel by lazy {
        ViewModelProvider(this,
            ResetViewModelFactory(ResetUseCase(ResetRepoImpl())))
            .get(ResetViewModel::class.java)
    }

    override fun getViewID(): Int = R.layout.activity_reset
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button_reset.visibility = View.VISIBLE
        button_reset.setOnClickListener {
            resetPass(editText_email_reset.text.toString().trim())
        }

        textView_go_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun resetPass(email: String) {

        if(!validateForm(email)) {
            return
        }

        resetViewModel.resetPassWord(email).observe(this,
            Observer { result ->
                when(result) {
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Success -> {
                        showToast("Cambio de contraseña exitoso")
                        hideProgressBar()
                    }
                    is Resource.Failure -> {
                        showToast("La Contraseña no se pudo cambiar.\n" +
                                "Intente nuevamente.")
                        hideProgressBar()
                    }
                }
            }
        )
    }

    private fun validateForm(email: String) : Boolean{

        if(TextUtils.isEmpty(email)) {
            showToast("Ingresar email válido!")
            return false
        }
        return true
    }

}
