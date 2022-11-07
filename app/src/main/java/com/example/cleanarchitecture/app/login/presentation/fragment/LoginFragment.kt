package com.example.cleanarchitecture.app.login.presentation.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.BaseFragment
import com.example.cleanarchitecture.app.home.activity.HomeActivity
import com.example.cleanarchitecture.app.login.presentation.viewmodels.DefaultLoginViewModel
import com.example.cleanarchitecture.app.login.presentation.viewmodels.LoginViewModel
import com.example.cleanarchitecture.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel : DefaultLoginViewModel by viewModels()
    override fun getLayout(): Int =R.layout.fragment_login
    override fun getViewModel(): ViewModel = viewModel

    override fun init() {

        viewModel.isNavigateTo.observe(requireActivity()) {
            startActivity(Intent(requireContext(), HomeActivity::class.java))
        }
    }


}