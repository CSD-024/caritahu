package com.dicoding.caritahu.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil.calculateDiff
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.ItemNewsBinding

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var data = listOf<NewsArticle>()

    fun setData(newData: List<NewsArticle>){
        val diffUtil = NewsDiffUtil(data, newData)
        val diffResults = calculateDiff(diffUtil)
        data = newData
        diffResults.dispatchUpdatesTo(this)
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsArticle) {
            binding.apply {
                tvTitle.text = article.title ?: "Not Included"
                tvDescription.text = article.description ?: "Not Included"

                Glide.with(root)
                    .load(article.urlToImage)
                    .into(ivThumbnail)

                root.setOnClickListener {
                    val action = NewsFragmentDirections.actionNewsFragmentToDetailFragment(article)
                    it.findNavController().navigate(action)
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