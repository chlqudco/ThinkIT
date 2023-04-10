package com.chlqudco.develop.thinkit.presentation.main

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.ActivityMainBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by inject<MainViewModel>()

    var userChoiceConcept: String = ""
    var userChoiceKeyword: String = ""

    private var isKeywordsView: Boolean = false
    private var isExplanationView: Boolean = false

    override fun getViewBinding()= ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        isKeywordsView = false
        isExplanationView = false
    }

    //네비그래프와 바텀네비 연결
    private fun initViews(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.ActivityMainNavigationHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()

        binding.ActivityMainBottomNavigationView.apply {
            setupWithNavController(navController)
            setOnItemSelectedListener { item ->
                NavigationUI.onNavDestinationSelected(item, navController)
                navController.popBackStack(item.itemId, inclusive = false)
                true
            }
        }
    }

    override fun observeData() {}

    fun setTopTextViewText(title: String){
        binding.ActivityMainTitleTextView.text = title
    }

    fun changeFragmentConceptToKeywords(concept: String){
        //이름 바꾸기
        binding.ActivityMainTitleTextView.text = concept
        userChoiceConcept = concept

        isKeywordsView = true
        isExplanationView = false
    }

    fun changeFragmentKeywordsToExplanation(keyword: String){
        //이름 바꾸기
        binding.ActivityMainTitleTextView.text = keyword
        userChoiceKeyword = keyword

        isKeywordsView = false
        isExplanationView = true
    }

    fun getSubject(): String{
        return binding.ActivityMainTitleTextView.text.toString()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if(isKeywordsView){
            binding.ActivityMainTitleTextView.text = "컴퓨터 공학"

            isKeywordsView = false
        } else if(isExplanationView){
            binding.ActivityMainTitleTextView.text = userChoiceConcept

            isKeywordsView = true
        }
        isExplanationView = false
    }

}