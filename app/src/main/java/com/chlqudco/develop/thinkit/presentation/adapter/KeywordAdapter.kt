package com.chlqudco.develop.thinkit.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.thinkit.R
import com.chlqudco.develop.thinkit.databinding.ItemKeywordBinding

class KeywordAdapter(
    val userToken: String,
    val keywordClickListener: (String) -> (Unit),
    val favoriteClickListener: (String, Boolean) -> (Unit)
): RecyclerView.Adapter<KeywordAdapter.ViewHolder>() {

    var keywordList : List<String> = emptyList()

    inner class ViewHolder(
        private val binding: ItemKeywordBinding
    ): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(keyword: String){
            //키워드 넣기
            binding.itemKeywordTextView.text = keyword

            //키워드 클릭 이벤트 리스너 넣기
            binding.root.setOnClickListener {
                keywordClickListener(keyword)
            }

            //즐겨찾기 추가 이벤트 리스너 넣기
            binding.itemKeywordFavoriteAddImageView.setOnClickListener {
                if (userToken.isNotEmpty()){
                    binding.itemKeywordFavoriteAddImageView.isVisible = false
                    binding.itemKeywordFavoriteCancelImageView.isVisible = true
                }

                favoriteClickListener(keyword, false)
            }

            //즐겨찾기 삭제 이벤트 리스너 넣기
            binding.itemKeywordFavoriteCancelImageView.setOnClickListener {
                if (userToken.isNotEmpty()){
                    binding.itemKeywordFavoriteAddImageView.isVisible = true
                    binding.itemKeywordFavoriteCancelImageView.isVisible = false
                }
                favoriteClickListener(keyword, true)
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