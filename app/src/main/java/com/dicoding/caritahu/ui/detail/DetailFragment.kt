package com.dicoding.caritahu.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(args.article)
    }

    private fun setData(article: NewsArticle) {
        val author = article.author ?: "Penulis"
        val meta = "Ditulis oleh ${author}, ${article.source?.name} pada tanggal ${article.publishedAt}"

        binding.apply {
            Glide.with(root)
                .load(article.urlToImage)
                .into(ivImage)

            tvTitle.text = article.title
            tvMeta.text = meta
            tvDescription.text = article.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}