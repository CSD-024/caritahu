package com.dicoding.caritahu.view.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dicoding.caritahu.R
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.FragmentDetailBinding
import com.dicoding.caritahu.helper.TextViewHelper.newsDetailDate
import com.dicoding.caritahu.viewmodel.DetailViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactory
import com.tapadoo.alerter.Alerter

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[DetailViewModel::class.java]

        viewModel.getBookmarked(args.article.title)
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

        val topBar = binding.toolbar

        topBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        topBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_share -> {
                    share(args.article.title, args.article.url!!)
                    true
                }
                else -> false
            }
        }

        setData(args.article)

        viewModel.isBookmarked.observe(viewLifecycleOwner, {
            if (it) {
                binding.fabBookmark.apply {
                    setImageResource(R.drawable.ic_bookmarked)
                    setOnClickListener {
                        viewModel.remove(args.article)
                        Alerter.create(requireActivity())
                            .setTitle(args.article.title)
                            .setText("Dihapus Dari Bookmarks")
                            .setBackgroundColorRes(R.color.purple_700)
                            .setIcon(R.drawable.ic_bookmark)
                            .setDuration(4000)
                            .show()
                    }
                }
            } else {
                binding.fabBookmark.apply {
                    setImageResource(R.drawable.ic_bookmark)
                    setOnClickListener {
                        viewModel.insert(args.article)
                        Alerter.create(requireActivity())
                            .setTitle(args.article.title)
                            .setText("Ditambahkan Ke Bookmarks")
                            .setBackgroundColorRes(R.color.purple_700)
                            .setIcon(R.drawable.ic_bookmarked)
                            .setDuration(4000)
                            .setOnClickListener {
                                findNavController().navigate(R.id.bookmarkFragment)
                            }
                            .show()
                    }
                }
            }
        })
    }

    private fun setData(article: NewsArticle) {
        val author = article.author ?: "Penulis"
        val meta = resources.getString(
            R.string.news_writer,
            author,
            newsDetailDate(article.publishedAt!!)
        )

        binding.apply {
            Glide.with(root)
                .load(article.urlToImage)
                .into(ivImage)

            tvTitle.text = article.title
            tvMeta.text = meta
            tvDescription.text = article.description

            btnWeb.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentToWebViewFragment(article.url)
                findNavController().navigate(action)
            }
        }
    }

    private fun share(title: String, url: String) {

        val message = resources.getString(R.string.share_news, title, url)

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