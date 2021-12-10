package com.dicoding.caritahu.view.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.BookmarkFragmentBinding
import com.dicoding.caritahu.viewmodel.BookmarkViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactory

class BookmarkFragment : Fragment() {

    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding as BookmarkFragmentBinding
    private lateinit var viewModel: BookmarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[BookmarkViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAll().observe(viewLifecycleOwner, { bookmarks ->
            if (bookmarks.isNotEmpty()) {
                switchView(true)
                setupRV(bookmarks)
            } else {
                switchView(false)
            }
        })
    }

    private fun setupRV(bookmarks: List<NewsArticle>) {
        val adapter = BookmarkAdapter()
        adapter.setData(bookmarks)
        binding.rv.apply {
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

    private fun switchView(condition: Boolean) {
        binding.tvEmpty.visibility = if (condition) View.GONE else View.VISIBLE
        binding.rv.visibility = if (condition) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}