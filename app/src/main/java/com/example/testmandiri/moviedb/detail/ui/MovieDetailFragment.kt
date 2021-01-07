package com.example.testmandiri.moviedb.detail.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.testmandiri.Injection
import com.example.testmandiri.R
import com.example.testmandiri.databinding.MovieDetailFragmentBinding
import com.example.testmandiri.review.ui.ReviewFragment
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewModel: MovieDetailViewModel

    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getInt(ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, Injection.provideMovieDetailViewModelFactory())
            .get(MovieDetailViewModel::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieDetail(id.toString())
                .observe(viewLifecycleOwner) {
                    binding.apply {
                        movie = it
                    }

                    if (it.video) {
                        initTrailer()
                    }
                }
        }

        binding.btnReview.setOnClickListener {
            val bundle = bundleOf(ReviewFragment.ID to id.toString())
            binding.root.findNavController()
                .navigate(R.id.action_movieDetailFragment_to_reviewFragment, bundle)
        }


        return binding.root
    }

    private fun initTrailer() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getVideo(id.toString())
                .observe(viewLifecycleOwner) { video ->

                    val youtubeUrl = "<html><body>Trailer<br>" +
                            "<iframe width=\"100%\" src=\"https://www.youtube.com/embed/" + video?.key + "\" " +
                            "frameborder=\"0\" allowfullscreen></iframe>" +
                            "</body></html>"

                    binding.youtubePlayer.webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            return false
                        }
                    }

                    binding.youtubePlayer.settings.javaScriptEnabled = true
                    binding.youtubePlayer.loadData(youtubeUrl, "text/html", "utf-8")
                }
        }
    }

    companion object {
        val ID: String = "id"
    }
}