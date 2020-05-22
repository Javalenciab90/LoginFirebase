package com.java90.pruebamultimedialab.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    override fun getViewID(): Int = R.layout.fragment_login
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getViewID(), container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_signIn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        tv_btn_go_register.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}