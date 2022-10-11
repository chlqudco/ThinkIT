package com.chlqudco.develop.thinkit.presentation.quiz.multiplechoice.result

import android.content.Intent
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

        val delayTime = Intent().getLongExtra("time",9999L)

        val minute = delayTime/1000/60
        val seconds = delayTime

        binding.ActivityMultipleChoiceResultTimeTextView.text = "${minute}분 ${seconds}초"
    }

    private fun initViews() {
        binding.ActivityMultipleChoiceResultExitButton.setOnClickListener {
            finish()
        }
    }

}