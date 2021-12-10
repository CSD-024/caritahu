package com.dicoding.caritahu.helper

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.caritahu.data.network.model.HoaxArticle

class HoaxDiffUtil(
    private val oldList: List<HoaxArticle>,
    private val newList: List<HoaxArticle>
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