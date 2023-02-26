package com.chlqudco.develop.thinkit.presentation.quiz.subjective.quiz

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.chlqudco.develop.thinkit.R

//커스텀 버튼
class RecordButton(context: Context, attrs : AttributeSet) : AppCompatImageButton(context, attrs) {
    //둥근 모양의 drawable 파일로 초기화
    init {
        setBackgroundResource(R.drawable.shape_oval_button)
    }

    //상태에 따라 어떤 아이콘을 세팅할지
    fun updateIconWithState(state: RecordState){
        when(state){
            RecordState.BEFORE_RECORDING -> {
                setImageResource(R.drawable.ic_record)
            }
            RecordState.ON_RECORDING -> {
                setImageResource(R.drawable.ic_stop)
            }
            RecordState.AFTER_RECORDING -> {
                setImageResource(R.drawable.ic_play)
            }
            RecordState.ON_PLAYING -> {
                setImageResource(R.drawable.ic_stop)
            }
        }
    }
}