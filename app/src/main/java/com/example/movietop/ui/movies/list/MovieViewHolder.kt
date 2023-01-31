package com.example.movietop.ui.movies.list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movietop.databinding.ItemMovieBinding
import com.example.movietop.domain.movies.Film

class MovieViewHolder(
    private val binding: ItemMovieBinding
) : ViewHolder(binding.root) {
    fun bindView(film: Film) {
        with(binding) {
            tvName.text = film.name
        }
    }
}