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
}