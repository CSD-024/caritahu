package com.dicoding.caritahu.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.caritahu.data.network.model.NewsArticle

class DiffUtil(
    private val oldList: List<NewsArticle>,
    private val newList: List<NewsArticle>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  when {
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }
            else -> true
        }
    }
}