package com.chlqudco.develop.thinkit.presentation.quiz.subjective.result

import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveResultBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import com.chlqudco.develop.thinkit.utility.AppKey.SUBJECTIVE_QUIZ_TIME
import org.koin.android.ext.android.inject

internal class SubjectiveResultActivity : BaseActivity<SubjectiveResultViewModel, ActivitySubjectiveResultBinding>() {

    override val viewModel: SubjectiveResultViewModel by inject()

    override fun getViewBinding() = ActivitySubjectiveResultBinding.inflate(layoutInflater)

    override fun observeData() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {

        val delayTime = intent.getLongExtra(SUBJECTIVE_QUIZ_TIME, 0)

        val minute = delayTime/1000/60
        val seconds = delayTime/1000%60

        binding.ActivitySubjectiveResultTimeTextView.text = "${minute}분 ${seconds}초"

        binding.ActivitySubjectiveResultExitButton.setOnClickListener {
            finish()
        }
    }
}