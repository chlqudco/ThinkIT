package com.chlqudco.develop.thinkit.presentation.base

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chlqudco.develop.thinkit.utility.AppKey
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent

internal abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>: Fragment() {
    var sToast: Toast? = null


    private val sharedPreferences: SharedPreferences by inject()

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetchJob: Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchJob = viewModel.fetchData()
        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        if (fetchJob.isActive){
            fetchJob.cancel()
        }
        super.onDestroy()
    }

    //토스트 메세지 띄우기
    fun showToastMessage(message: String) {
        if (sToast == null) {
            sToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        } else {
            sToast!!.cancel()
            Thread.sleep(100)
            sToast!!.setText(message)
        }
        sToast?.show()
    }

}