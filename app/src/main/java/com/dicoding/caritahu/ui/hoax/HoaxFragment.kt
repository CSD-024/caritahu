package com.dicoding.caritahu.ui.hoax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.databinding.HoaxFragmentBinding

class HoaxFragment : Fragment() {

    private var _binding: HoaxFragmentBinding? = null
    private val binding get() = _binding as HoaxFragmentBinding

    private lateinit var viewModel: HoaxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HoaxViewModel::class.java]
        viewModel.getLatestHoax()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HoaxFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.latestHoax.observe(viewLifecycleOwner, { articles ->
            setupRV(articles)
        })
    }

    private fun setupRV(articles: List<HoaxArticle>){
        val adapter = HoaxAdapter()
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