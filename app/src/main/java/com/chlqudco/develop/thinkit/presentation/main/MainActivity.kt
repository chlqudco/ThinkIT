package com.chlqudco.develop.thinkit.presentation.main

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.ActivityMainBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.concept.ConceptFragment
import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationFragment
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsFragment
import com.chlqudco.develop.thinkit.presentation.quiz.QuizFragment
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

        //바텀 내비게이션 클릭 리스너
        binding.mainBottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.conceptFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.mainNavigationHostFragment, ConceptFragment()
                    ).commit()
                }
                R.id.quizFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.mainNavigationHostFragment, QuizFragment()
                    ).commit()
                }
            }
            true
        }
    }

    override fun observeData() {

    }

    fun setTopTextViewText(title: String){
        binding.MainTitleTextView.text = title
    }

    fun changeFragmentConceptToKeywords(){

        supportFragmentManager.beginTransaction().replace(
            R.id.mainNavigationHostFragment, KeywordsFragment()
        ).commit()

        //Navigation.findNavController(binding.MainTitleTextView).navigate(R.id.action_quizFragment_to_keywordsFragment)
    }

    fun changeFragmentKeywordsToExplanation(){

        supportFragmentManager.beginTransaction().replace(
            R.id.mainNavigationHostFragment, ExplanationFragment()
        ).commit()

        //Navigation.findNavController(binding.MainTitleTextView).navigate(R.id.action_quizFragment_to_keywordsFragment)
    }
}