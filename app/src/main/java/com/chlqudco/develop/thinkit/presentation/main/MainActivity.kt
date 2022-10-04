package com.chlqudco.develop.thinkit.presentation.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.ActivityMainBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by inject<MainViewModel>()

    override fun getViewBinding()= ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    //네비그래프와 바텀네비 연결
    private fun initViews(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainNavigationHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.mainBottomNavigationView.setupWithNavController(navController)
    }

    override fun observeData() {

    }

    fun setTopTextViewText(title: String){
        binding.MainTitleTextView.text = title
    }
}