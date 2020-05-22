package com.java90.pruebamultimedialab.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.java90.pruebamultimedialab.R

abstract class BaseFragment : Fragment() {

    abstract fun getViewID(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return inflater.inflate(getViewID(), container, false)
    }
    
    fun showToast(msg : String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
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