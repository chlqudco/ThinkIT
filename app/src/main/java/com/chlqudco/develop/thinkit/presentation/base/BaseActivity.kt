package com.chlqudco.develop.thinkit.presentation.base

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.chlqudco.develop.thinkit.utility.AppKey
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject

internal abstract class BaseActivity<VM: BaseViewModel, VB: ViewBinding>: AppCompatActivity() {
    var sToast: Toast? = null

    private val sharedPreferences: SharedPreferences by inject()

    abstract val viewModel: VM

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private lateinit var fetchJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = getViewBinding()
        setContentView(binding.root)

        fetchJob = viewModel.fetchData()
        observeData()

        //다크모드 금지
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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
            sToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        } else {
            sToast!!.cancel()
            Thread.sleep(100)
            sToast!!.setText(message)
        }
        sToast?.show()
    }

}