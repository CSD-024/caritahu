package com.dicoding.caritahu.view.detailhoax

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dicoding.caritahu.R
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.databinding.FragmentDetailHoaxBinding

class DetailHoaxFragment : Fragment() {

    private var _binding: FragmentDetailHoaxBinding? = null
    private val binding get() = _binding as FragmentDetailHoaxBinding

    private val args: DetailHoaxFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailHoaxBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        setup(args.hoax)
    }

    private fun setup(hoax: HoaxArticle) {
        binding.apply {
            Glide.with(root)
                .load(hoax.picture1)
                .into(ivImage)

            tvTitle.text = hoax.title
            tvSource.text = "Sumber media ${hoax.source_issue}, tanggal ${hoax.date}"
            tvConclusion.text = hoax.conclusion
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}