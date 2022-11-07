package com.example.cleanarchitecture.app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cleanarchitecture.app.viewmodels.MainViewModel
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.app.base.BaseActivity
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
   private  val viewModel: MainViewModel by viewModels()
    override fun getLayout(): Int = R.layout.activity_main
    override fun setUp() {
        super.setUp()
        installSplashScreen().setKeepOnScreenCondition{
            viewModel._splashLoadingState.value
        }
    }

    override fun init() {
        setNavHostFragment(R.id.nav_host_fragment)
        setNavController()
    }


    /*  override fun onCreate(savedInstanceState: Bundle?) {
          installSplashScreen().setKeepOnScreenCondition{
              viewModel._splashLoadingState.value
          }
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_main)
      }*/
}