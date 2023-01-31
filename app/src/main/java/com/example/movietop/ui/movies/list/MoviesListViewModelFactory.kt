package com.example.movietop.ui.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietop.domain.movies.MoviesRepository
import javax.inject.Inject

class MoviesListViewModelFactory @Inject constructor(
    private val repository: MoviesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == MoviesListViewModel::class.java) {
            return MoviesListViewModel(repository = repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModelClass")
    }
}