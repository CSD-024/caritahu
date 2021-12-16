package com.dicoding.caritahu.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dicoding.caritahu.R
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.FragmentDetailBinding
import com.dicoding.caritahu.viewmodel.DetailViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactory

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        val topBar = binding.toolbar

        topBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        topBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_share -> {
                    println("OK saya share")
                    true
                }
                else -> false
            }
        }

        setData(article)
        viewModel.getBookmarked(article.title)
        viewModel.isBookmarked.observe(viewLifecycleOwner, {
            println("Bookmark: $it")
            if (it) {
                binding.fabBookmark.apply {
                    setImageResource(R.drawable.ic_bookmarked)
                    setOnClickListener {
                        viewModel.remove(article)
                    }
                }
            } else {
                binding.fabBookmark.apply {
                    setImageResource(R.drawable.ic_bookmark)
                    setOnClickListener {
                        viewModel.insert(article)
                    }
                }
            }
        })
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