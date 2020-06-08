package com.java90.pruebamultimedialab.ui.main.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.java90.pruebamultimedialab.R
import com.java90.pruebamultimedialab.data.network.ProfileUserRepoImp
import com.java90.pruebamultimedialab.domain.usecases.ProfileUseCase
import com.java90.pruebamultimedialab.ui.main.BaseFragment
import com.java90.pruebamultimedialab.utils.Resource
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : BaseFragment() {
    override fun getViewID(): Int = R.layout.fragment_profile

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 0;
    }
    private lateinit var viewModel: ProfileViewModel
    private lateinit var imageUri: Uri


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val repository = ProfileUserRepoImp()
        val viewModelFactory = ProfileUseCase(repository)
        viewModel = ViewModelProvider(this, ProfileViewModelFactory(viewModelFactory))
            .get(ProfileViewModel::class.java)


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
        startActivityForResult(intent, IMAGE_PICK_CODE)
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
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}