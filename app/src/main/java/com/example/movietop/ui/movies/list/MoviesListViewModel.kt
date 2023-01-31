package com.example.movietop.ui.movies.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movietop.data.movies.FilmDTO
import com.example.movietop.domain.movies.Film
import com.example.movietop.domain.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MoviesListViewModel(
    private val repository: MoviesRepository
) : ViewModel() {

    val filmsFlow: Flow<PagingData<Film>> = repository
        .getPagedMovies()
        .cachedIn(viewModelScope)

}