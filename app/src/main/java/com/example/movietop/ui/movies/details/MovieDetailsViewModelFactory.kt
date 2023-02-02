package com.example.movietop.ui.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietop.domain.movies.details.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsViewModelFactory @Inject constructor(
    private val repository: MovieDetailsRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MovieDetailsViewModel::class.java) {
            return MovieDetailsViewModel(repository = repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}