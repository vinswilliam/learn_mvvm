package com.example.testmandiri.moviedb.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.testmandiri.Injection
import com.example.testmandiri.databinding.ListMovieFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListMovieDbFragment : Fragment() {

    private lateinit var binding: ListMovieFragmentBinding
    private lateinit var viewModel: MovieViewModel

    private val adapter: MovieAdapter = MovieAdapter()

    private var searchJob: Job? = null

    private var ids: Array<String>? = null

    private fun search(ids: Array<String>) {
        if (ids.isNotEmpty()) {
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                viewModel.getMovie(ids).collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ids = arguments?.getStringArray(IDS)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListMovieFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity(), Injection.provideMovieViewModelFactory())
            .get(MovieViewModel::class.java)

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.listMovie.addItemDecoration(decoration)

        binding.listMovie.adapter = adapter
        initAdapter()

        ids?.let {
            search(it)
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray(LAST_SEARCH_MOVIE, ids)
    }

    private fun initAdapter() {

    }

    companion object {
        private const val LAST_SEARCH_MOVIE: String = "last_search_movie"
        const val IDS = "ids"
    }
}