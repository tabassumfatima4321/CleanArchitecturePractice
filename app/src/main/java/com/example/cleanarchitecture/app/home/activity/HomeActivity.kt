package com.example.cleanarchitecture.app.home.activity


import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<com.example.cleanarchitecture.databinding.ActivityHomeBinding>() {
    override fun init()
    {
        setNavHostFragment(R.id.nav_host_fragment_home)
        setNavController()
    }

    override fun getLayout(): Int =R.layout.activity_home

}