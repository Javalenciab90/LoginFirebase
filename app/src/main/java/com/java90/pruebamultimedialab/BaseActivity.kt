package com.java90.pruebamultimedialab

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewID())
    }

    abstract fun getViewID(): Int

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    fun showToast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun errorMessageFirebase(exceptionMsg: String) {
        when(exceptionMsg) {
            "A network error (such as timeout, interrupted connection or unreachable host) has occurred." -> {
                showToast("Error en la red. Verifique su conexión.")
            }
            "There is no user record corresponding to this identifier. The user may have been deleted." -> {
                showToast("El email no corresponde o no existe.")
            }
            "The password is invalid or the user does not have a password." -> {
                showToast("La contraseña no corresponde. Vuelva a intentarlo.")
            }
            else -> showToast("Error desconocido")
        }
    }
}