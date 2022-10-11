package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result

import android.os.Bundle
import com.chlqudco.develop.thinkit.databinding.ActivityMultipleChoiceResultBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseActivity
import org.koin.android.ext.android.inject

internal class MultipleChoiceResultActivity : BaseActivity<MultipleChoiceResultViewModel, ActivityMultipleChoiceResultBinding>() {


    override val viewModel: MultipleChoiceResultViewModel by inject()

    override fun getViewBinding() = ActivityMultipleChoiceResultBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.ActivityMultipleChoiceResultExitButton.setOnClickListener {
            finish()
        }
    }

}