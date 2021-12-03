package com.dicoding.caritahu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.caritahu.data.network.Article
import com.dicoding.caritahu.databinding.ItemNewsBinding

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var data = listOf<Article>()

    fun setData(newData: List<Article>){
        val diffUtil = DiffUtil(data, newData)
        val diffResults = calculateDiff(diffUtil)
        data = newData
        diffResults.dispatchUpdatesTo(this)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                tvTitle.text = article.title ?: "Not Included"
                tvDescription.text = article.description ?: "Not Included"

                Glide.with(root)
                    .load(article.urlToImage)
                    .into(ivThumbnail)

                root.setOnClickListener {
                    println(article.url)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}