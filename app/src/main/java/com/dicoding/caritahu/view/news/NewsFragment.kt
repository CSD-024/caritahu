package com.dicoding.caritahu.view.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.caritahu.R
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.NewsFragmentBinding
import com.dicoding.caritahu.viewmodel.NewsViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactory

class NewsFragment : Fragment() {

    private var _binding: NewsFragmentBinding? = null
    private val binding get() = _binding as NewsFragmentBinding

    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java]
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

        viewModel.status.observe(viewLifecycleOwner, { status ->
            binding.pb.visibility = if (status) View.GONE else View.VISIBLE
        })

        viewModel.headlines.observe(viewLifecycleOwner, { articles ->
            setupRV(articles)
        })

        val topBar = binding.toolbar

        topBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    val action = NewsFragmentDirections.actionNewsFragmentToSearchFragment("news")
                    findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRV(articles: List<NewsArticle>){
        val adapter = NewsAdapter("list")
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