package com.example.testmandiri.review.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.testmandiri.Injection
import com.example.testmandiri.databinding.ReviewFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ReviewFragment : Fragment() {

    private lateinit var binding: ReviewFragmentBinding
    private lateinit var viewModel: ReviewViewModel

    private val adapter: ReviewAdapter = ReviewAdapter()

    private var job: Job? = null

    private var id: String? = null

    private fun getReview(id: String) {
        if (id.isNotEmpty()) {
            job?.cancel()
            job = lifecycleScope.launch {
                viewModel.getReview(id).collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString(ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReviewFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, Injection.provideReviewViewModelFactory())
            .get(ReviewViewModel::class.java)

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.listReview.addItemDecoration(decoration)
        binding.listReview.adapter = adapter

        initAdapter()

        id?.let {
            getReview(it)
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_REVIEW_ID, id)
    }

    private fun initAdapter() {
        adapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading
                && loadState.append.endOfPaginationReached
                && adapter.itemCount < 1) {
                binding.listReview.isVisible = false
                binding.emptyView.isVisible = true
            } else {
                binding.listReview.isVisible = true
                binding.emptyView.isVisible = false
            }

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.append as? LoadState.Error

            errorState?.let {
                Toast.makeText(requireContext(), "\uD83D\uDE28 Upps, ${it.error}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    companion object {
        private const val LAST_REVIEW_ID: String = "last_review_id"
        const val ID: String = "id"
    }
}