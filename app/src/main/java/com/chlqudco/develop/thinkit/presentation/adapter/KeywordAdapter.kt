package com.chlqudco.develop.thinkit.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.thinkit.databinding.ItemKeywordBinding

class KeywordAdapter(
    val keywordClickListener: (String) -> (Unit)
): RecyclerView.Adapter<KeywordAdapter.ViewHolder>() {

    var keywordList : List<String> = emptyList()

    inner class ViewHolder(
        private val binding: ItemKeywordBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(keyword: String){
            binding.itemKeywordTextView.text = keyword
            binding.root.setOnClickListener {
                keywordClickListener(keyword)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(keywordList[position])
    }

    override fun getItemCount(): Int {
        return keywordList.size
    }
}