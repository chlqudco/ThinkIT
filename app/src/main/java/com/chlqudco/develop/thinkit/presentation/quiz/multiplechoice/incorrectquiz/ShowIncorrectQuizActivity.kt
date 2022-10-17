package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.incorrectquiz

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.chlqudco.develop.thinkit.databinding.ActivityShowIncorrectQuizBinding
import com.chlqudco.develop.thinkit.presentation.adapter.InCorrectAdapter
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.utility.AppKey
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_INCORRECT_BOGI
import com.chlqudco.develop.thinkit.utility.AppKey.MULTIPLE_INCORRECT_QUIZ
import org.koin.android.ext.android.inject

internal class ShowIncorrectQuizActivity : BaseActivity<ShowIncorrectQuizViewModel, ActivityShowIncorrectQuizBinding>() {

    override val viewModel: ShowIncorrectQuizViewModel by inject()

    override fun getViewBinding() = ActivityShowIncorrectQuizBinding.inflate(layoutInflater)

    override fun observeData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        //어댑터 연결
        val adapter = InCorrectAdapter()
        //정보를 넘겨줘야 함
        adapter.quizList = intent.getStringArrayListExtra(MULTIPLE_INCORRECT_QUIZ) ?: arrayListOf()
        adapter.bogiList = intent.getStringArrayListExtra(MULTIPLE_INCORRECT_BOGI) ?: arrayListOf()

        binding.ActivityShowIncorrectQuizRecyclerView.adapter = adapter
        binding.ActivityShowIncorrectQuizRecyclerView.layoutManager = LinearLayoutManager(this)

        //닫기 버튼
        binding.ActivityShowIncorrectQuizCloseButton.setOnClickListener {
            finish()
        }
    }

}