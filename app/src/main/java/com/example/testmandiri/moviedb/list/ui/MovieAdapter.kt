package com.example.testmandiri.moviedb.list.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testmandiri.R
import com.example.testmandiri.databinding.ItemMovieBinding
import com.example.testmandiri.moviedb.detail.ui.MovieDetailFragment
import com.example.testmandiri.moviedb.list.data.MovieDB

class MovieAdapter : PagingDataAdapter<MovieDB, MovieAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.apply {
                bind(movie)
                itemView.tag = movie
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDB) {
            binding.apply {
                movie = item
                root.setOnClickListener {
                    val bundle = bundleOf(MovieDetailFragment.ID to item.id)
                    binding.root.findNavController()
                        .navigate(R.id.action_listMovieDbFragment_to_movieDetailFragment, bundle)
                }
                executePendingBindings()
            }
        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<MovieDB>() {
    override fun areItemsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDB, newItem: MovieDB): Boolean {
        return oldItem == newItem
    }
}