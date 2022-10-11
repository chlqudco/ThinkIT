package com.chlqudco.develop.thinkit.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.ActivityMainBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.presentation.concept.ConceptFragment
import com.chlqudco.develop.thinkit.presentation.explanation.ExplanationFragment
import com.chlqudco.develop.thinkit.presentation.keywords.KeywordsFragment
import com.chlqudco.develop.thinkit.presentation.quiz.QuizChoiceFragment
import org.koin.android.ext.android.inject

internal class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by inject<MainViewModel>()

    var userChoiceConcept: String = ""
    var userChoiceKeyword: String = ""

    override fun getViewBinding()= ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    //네비그래프와 바텀네비 연결
    private fun initViews(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.ActivityMainNavigationHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.ActivityMainBottomNavigationView.setupWithNavController(navController)

        //바텀 내비게이션 클릭 리스너
        binding.ActivityMainBottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.conceptFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.ActivityMainNavigationHostFragment, ConceptFragment()
                    ).commit()
                }
                R.id.quizFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.ActivityMainNavigationHostFragment, QuizChoiceFragment()
                    ).commit()
                }
            }
            true
        }
    }

    override fun observeData() {}

    fun setTopTextViewText(title: String){
        binding.ActivityMainTitleTextView.text = title
    }

    fun changeFragment(destinationFragment: Fragment){
        supportFragmentManager.beginTransaction().replace(
            R.id.ActivityMainNavigationHostFragment, destinationFragment
        ).commit()
    }

    fun changeFragmentConceptToKeywords(concept: String){

        //이름 바꾸기
        binding.ActivityMainTitleTextView.text = concept
        userChoiceConcept = concept

        supportFragmentManager.beginTransaction().add(
            R.id.ActivityMainNavigationHostFragment, KeywordsFragment()
        ).commit()

        //Navigation.findNavController(binding.MainTitleTextView).navigate(R.id.action_quizFragment_to_keywordsFragment)
    }

    fun changeFragmentKeywordsToExplanation(keyword: String){

        //이름 바꾸기
        binding.ActivityMainTitleTextView.text = keyword
        userChoiceKeyword = keyword

        supportFragmentManager.beginTransaction().add(
            R.id.ActivityMainNavigationHostFragment, ExplanationFragment()
        ).commit()

        //Navigation.findNavController(binding.MainTitleTextView).navigate(R.id.action_quizFragment_to_keywordsFragment)
    }

    fun getSubject(): String{
        return when(binding.ActivityMainTitleTextView.text.toString()){
            "자료 구조"-> "dataStructure"
            "알고리즘"-> "algorithm"
            "데이터베이스" -> "database"
            "운영체제" -> "os"
            "네트워크" -> "네트워크"
            else -> "오류"
        }
    }
}