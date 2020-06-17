package com.java90.firebasetesting.ui.main.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.java90.firebasetesting.R
import com.java90.firebasetesting.ui.BaseFragment
import com.java90.firebasetesting.ui.main.MainActivity
import com.java90.firebasetesting.ui.main.MainViewModel
import com.java90.firebasetesting.utils.Resource
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_profile

    private lateinit var viewModel: MainViewModel

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 0;
    }

    private lateinit var imageUri: Uri

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = (activity as MainActivity).viewModel

        viewModel.initProfileUser().observe(viewLifecycleOwner,
            Observer { response ->
                when(response) {
                    is Resource.Loading -> {
                        showToast("Loading....")
                    }
                    is Resource.Success -> {
                        Glide.with(this)
                            .load(response.data)
                            .into(ivPhotoUser)
                    }
                    is Resource.Failure -> {
                        showToast(response.data.toString())
                    }
                }
            }
        )

        btn_selectPhoto.setOnClickListener {
            pickPhotoFromGallery()
        }

        btn_updateProfile.setOnClickListener {
            viewModel.updateProfile(imageUri, etUsername.text.toString())
                .observe(viewLifecycleOwner, Observer { response ->
                        when(response) {
                            is Resource.Loading -> {
                                showToast("Loading....")
                            }
                            is Resource.Success -> {
                                showToast(response.data.toString())
                            }
                            is Resource.Failure -> {
                                errorMessageFirebase(response.data.toString())
                            }
                        }
                    }
                )
        }
    }

    private fun pickPhotoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,
            IMAGE_PICK_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data!!
        }else {
            showToast("Image not selected.")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_sign_out, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_signOut -> {
                viewModel.signOut()
                view?.findNavController()?.navigate(R.id.navigate_to_loginFragment)
                (activity as MainActivity).finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}