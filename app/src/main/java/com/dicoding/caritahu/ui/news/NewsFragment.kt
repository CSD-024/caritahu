package com.dicoding.caritahu.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.NewsFragmentBinding

class NewsFragment : Fragment() {

    private var _binding: NewsFragmentBinding? = null
    private val binding get() = _binding as NewsFragmentBinding

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]
        viewModel.topHeadlines()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.headlines.observe(viewLifecycleOwner, { articles ->
            setupRV(articles)
        })
    }

    private fun setupRV(articles: List<NewsArticle>){
        val adapter = NewsAdapter()
        adapter.setData(articles)
        binding.rv.apply {
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}