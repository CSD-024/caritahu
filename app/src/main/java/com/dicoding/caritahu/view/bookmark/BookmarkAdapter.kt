package com.dicoding.caritahu.view.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.ItemNewsBinding
import com.dicoding.caritahu.helper.NewsDiffUtil

class BookmarkAdapter: RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {
    private var data = listOf<NewsArticle>()

    fun setData(newData: List<NewsArticle>){
        val diffUtil = NewsDiffUtil(data, newData)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        data = newData
        diffResults.dispatchUpdatesTo(this)
    }

    inner class BookmarkViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsArticle) {
            binding.apply {
                tvTitle.text = article.title
                tvDescription.text = article.description ?: "Not Included"

                Glide.with(root)
                    .load(article.urlToImage)
                    .into(ivThumbnail)

                root.setOnClickListener {
                    val action = BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(article)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}