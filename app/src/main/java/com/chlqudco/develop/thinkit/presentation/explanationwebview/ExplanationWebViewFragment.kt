package com.chlqudco.develop.thinkit.presentation.explanationwebview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.chlqudco.develop.thinkit.BuildConfig
import com.chlqudco.develop.thinkit.databinding.FragmentExplanationWebviewBinding
import com.chlqudco.develop.thinkit.presentation.base.BaseFragment
import com.chlqudco.develop.thinkit.presentation.main.MainActivity
import org.koin.android.ext.android.inject

internal class ExplanationWebViewFragment : BaseFragment<ExplanationWebViewViewModel, FragmentExplanationWebviewBinding>() {

    override val viewModel by inject<ExplanationWebViewViewModel>()

    override fun getViewBinding(): FragmentExplanationWebviewBinding =
        FragmentExplanationWebviewBinding.inflate(layoutInflater)

    override fun observeData() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {
        //웹 뷰에 링크 올리기
        binding.fragmentExplanationWebViewWebView.apply {
            val keyword = (activity as MainActivity).getSubject()
            val explanationUrl = "${BuildConfig.baseUrl}/bootstrap?keyword=$keyword"
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(explanationUrl)
        }
    }

    override fun onPause() {
        binding.fragmentExplanationWebViewWebView.onPause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding.fragmentExplanationWebViewWebView.onResume()
    }
}