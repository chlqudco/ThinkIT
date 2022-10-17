package com.chlqudco.develop.thinkit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.thinkit.databinding.ItemIncorrectQuizBinding

class InCorrectAdapter : RecyclerView.Adapter<InCorrectAdapter.ViewHolder>() {

    var quizList : List<String> = emptyList()
    var bogiList : List<String> = emptyList()

    inner class ViewHolder(
        private val binding: ItemIncorrectQuizBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(quiz: String, bogi: String){
            binding.ItemIncorrectQuizTextView.text = quiz
            binding.ItemIncorrectBogiTextView.text = bogi
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemIncorrectQuizBinding.inflate(LayoutInflater.from(parent.context), parent, false ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quizList[position], bogiList[position])
    }

    override fun getItemCount(): Int {
        return quizList.size
    }
}