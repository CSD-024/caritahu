package com.dicoding.caritahu.ui.hoax

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.databinding.ItemHoaxBinding

class HoaxAdapter(): RecyclerView.Adapter<HoaxAdapter.HoaxViewHolder>() {
    private var data = listOf<HoaxArticle>()

    fun setData(newData: List<HoaxArticle>) {
        val diffUtil = HoaxDiffUtil(data, newData)
        val diffResults = calculateDiff(diffUtil)
        data = newData
        diffResults.dispatchUpdatesTo(this)
    }

    inner class HoaxViewHolder(private val binding: ItemHoaxBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: HoaxArticle) {
            binding.apply {
                tvTitle.text = article.title

                Glide.with(root)
                    .load(article.picture1)
                    .into(ivThumbnail)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoaxViewHolder {
        val binding = ItemHoaxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HoaxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HoaxViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}