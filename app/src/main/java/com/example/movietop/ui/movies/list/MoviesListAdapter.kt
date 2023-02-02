package com.example.movietop.ui.movies.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.movietop.databinding.ItemMovieBinding
import com.example.movietop.domain.movies.list.Film

class MoviesListAdapter(
    private val onMovieClick: (id: Int) -> Unit
) : PagingDataAdapter<Film, MovieViewHolder>(DiffUtilsCallback()) {
    // 20 фильмов на страницу
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindView(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(
            binding = binding,
            onMovieClick = onMovieClick
        )
    }
}

private class DiffUtilsCallback : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}