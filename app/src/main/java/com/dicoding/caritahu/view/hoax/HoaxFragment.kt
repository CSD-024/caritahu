package com.dicoding.caritahu.view.hoax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dicoding.caritahu.R
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.databinding.HoaxFragmentBinding
import com.dicoding.caritahu.viewmodel.HoaxViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactory

class HoaxFragment : Fragment() {

    private var _binding: HoaxFragmentBinding? = null
    private val binding get() = _binding as HoaxFragmentBinding

    private lateinit var viewModel: HoaxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[HoaxViewModel::class.java]
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

        val topBar = binding.toolbar

        topBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    val action = HoaxFragmentDirections.actionHoaxFragmentToSearchFragment("hoax")
                    findNavController().navigate(action)
                    true
                }
                else -> false
            }
        }

        viewModel.status.observe(viewLifecycleOwner, {
            binding.pb.visibility = if (it) View.GONE else View.VISIBLE
        })

        viewModel.latestHoax.observe(viewLifecycleOwner, { articles ->
            setupRV(articles)
        })
    }

    private fun setupRV(articles: List<HoaxArticle>){
        val adapter = HoaxAdapter("list")
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