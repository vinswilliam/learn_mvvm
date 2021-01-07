package com.example.testmandiri.genre.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testmandiri.R
import com.example.testmandiri.databinding.ItemGenreBinding
import com.example.testmandiri.genre.data.Genre
import com.example.testmandiri.moviedb.list.ui.ListMovieDbFragment

class GenreAdapter(private var list: ArrayList<Genre>? = arrayListOf()) :
    RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    private lateinit var recyclerView: RecyclerView

    private fun getItem(position: Int): Genre? {
        return list?.get(position)
    }

    fun submit(item: Genre) {
        list?.add(item)
        notifyItemInserted(itemCount)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genreDetail = getItem(position)
        genreDetail?.let {
            holder.apply {
                bind(genreDetail)
                itemView.tag = genreDetail
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    class ViewHolder(
        private val binding: ItemGenreBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Genre) {
            binding.tvGenreName.text = item.name

            binding.root.setOnClickListener {
                val bundle = bundleOf(ListMovieDbFragment.IDS to Array(1) { item.id.toString() })
                binding.root.findNavController()
                    .navigate(R.id.action_listGenreFragment_to_listMovieDbFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

}