package com.dicoding.caritahu.view.detailhoax

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
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
import com.dicoding.caritahu.helper.TextViewHelper.convertToHtml
import com.dicoding.caritahu.helper.TextViewHelper.hoaxDetailDate
import com.dicoding.caritahu.helper.TextViewHelper.referenceStyling

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
                    share(args.hoax.title)
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
            tvSource.text = resources.getString(
                R.string.tv_source,
                hoax.source_issue,
                hoaxDetailDate(hoax.date)
            )

            tvContent.text = convertToHtml(hoax.content)
            tvFact.text = convertToHtml(hoax.fact)
            tvConclusion.text = hoax.conclusion
            tvReferences.text = referenceStyling(hoax.references)

            // Clickable Link on TextView
            tvContent.movementMethod = LinkMovementMethod.getInstance()
            tvFact.movementMethod = LinkMovementMethod.getInstance()
            tvConclusion.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun share(hoaxTitle: String) {
        val message = resources.getString(R.string.share_hoax, hoaxTitle)
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}