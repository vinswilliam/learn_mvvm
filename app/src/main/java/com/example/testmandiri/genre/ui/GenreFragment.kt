package com.example.testmandiri.genre.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.testmandiri.Injection
import com.example.testmandiri.databinding.ListGenreFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GenreFragment : Fragment() {

    private lateinit var binding: ListGenreFragmentBinding
    private lateinit var viewModel: GenreViewModel

    private val adapter: GenreAdapter = GenreAdapter()

    private var fetchGenreJob: Job? = null

    private fun fetchGenre() {
        fetchGenreJob?.cancel()

        fetchGenreJob = lifecycleScope.launch {
            viewModel.fetchGenre().collect {
                adapter.submit(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListGenreFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity(), Injection.provideGenreViewModelFactory())
            .get(GenreViewModel::class.java)

        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(decoration)

        binding.list.adapter = adapter
        fetchGenre()
        return binding.root
    }

}