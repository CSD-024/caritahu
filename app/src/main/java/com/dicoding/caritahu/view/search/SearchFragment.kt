package com.dicoding.caritahu.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.SearchFragmentBinding
import com.dicoding.caritahu.view.hoax.HoaxAdapter
import com.dicoding.caritahu.view.news.NewsAdapter
import com.dicoding.caritahu.viewmodel.SearchViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactory

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding as SearchFragmentBinding

    private lateinit var viewModel: SearchViewModel

    private val args: SearchFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topBar = binding.toolbar

        topBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val searchView = binding.svQuery

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                if (args.type == "news") {
                    viewModel.getSearchNews(query)
                } else {
                    viewModel.getSearchHoax(query)
                }
                return false
            }
        })

        viewModel.status.observe(viewLifecycleOwner, {
            when(it) {
                "Loading" -> binding.pb.visibility = View.VISIBLE
                "Success" -> binding.pb.visibility = View.GONE
                else -> {
                    Toast.makeText(
                        requireContext(),
                        it,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

        viewModel.searchHoax.observe(viewLifecycleOwner, {
            if (it != null) {
                setupRVHoax(it)
            }
        })

        viewModel.searchNews.observe(viewLifecycleOwner, {
            setupRVNews(it)
        })
    }

    private fun setupRVNews(list: List<NewsArticle>) {
        val adapter = NewsAdapter("search")
        adapter.setData(list)
        binding.rvSearch.apply {
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

    private fun setupRVHoax(list: List<HoaxArticle>) {
        val adapter = HoaxAdapter("search")
        adapter.setData(list)
        binding.rvSearch.apply {
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}