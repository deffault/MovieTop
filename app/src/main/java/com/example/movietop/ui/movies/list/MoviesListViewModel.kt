package com.example.movietop.ui.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movietop.domain.movies.list.Film
import com.example.movietop.domain.movies.list.MoviesListRepository
import kotlinx.coroutines.flow.Flow

class MoviesListViewModel(
    private val repository: MoviesListRepository
) : ViewModel() {

    val filmsFlow: Flow<PagingData<Film>> = repository
        .getPagedMovies()
        .cachedIn(viewModelScope)

}