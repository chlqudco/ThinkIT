package com.chlqudco.develop.thinkit.presentation.quiz.subjective.result

import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivitySubjectiveResultBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
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
        binding.ActivitySubjectiveResultExitButton.setOnClickListener {
            finish()
        }
    }
}