package com.example.cleanarchitecture.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB:ViewBinding>
    : AppCompatActivity()
{
    private lateinit var _binding:VB
    protected val binding get()=_binding
    private var navHostFragment:NavHostFragment?=null
    protected var navController:NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=DataBindingUtil.setContentView(this,getLayout())
        init()

    }
    open fun setUp(){}
    abstract fun init():Unit
    abstract fun getLayout():Int
    open fun setNavHostFragment(resId:Int)
    {
        navHostFragment=supportFragmentManager.findFragmentById(resId) as NavHostFragment
    }
    open fun setNavController()
    {
        navController=navHostFragment?.navController
    }
    open fun setUpToolbar(toolbar: Toolbar)
    {
        setSupportActionBar(toolbar)
        navController?.apply {
            val appConfiguration= AppBarConfiguration(this.graph)
            setupActionBarWithNavController(this,appConfiguration) }
    }
}