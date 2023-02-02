package com.example.movietop.ui.movies.list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.movietop.databinding.ItemMovieBinding
import com.example.movietop.domain.movies.Film

class MovieViewHolder(
    private val binding: ItemMovieBinding
) : ViewHolder(binding.root) {
    fun bindView(film: Film) {
        with(binding) {
            ivPoster.load(film.posterUrlPreview)
            tvName.text = film.name
            tvYear.text = film.year
        }
    }
}