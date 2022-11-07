package com.example.cleanarchitecture.app.home.coins.presentation.fragments


import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.BaseFragment
import com.example.cleanarchitecture.app.home.coins.presentation.viewmodels.DefaultCoinViewModel
import com.example.cleanarchitecture.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>()
{
    private val viewModel:DefaultCoinViewModel by viewModels()
    override fun getLayout(): Int = R.layout.fragment_home
    override fun getViewModel(): ViewModel = viewModel

    override fun init() {
    }



}