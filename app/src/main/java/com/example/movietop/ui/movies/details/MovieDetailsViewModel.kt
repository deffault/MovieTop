package com.example.movietop.ui.movies.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietop.domain.movies.details.MovieDetails
import com.example.movietop.domain.movies.details.MovieDetailsRepository
import com.example.movietop.domain.movies.details.actors.Actor
import com.example.movietop.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val repository: MovieDetailsRepository
) : ViewModel() {

    private val _details = MutableStateFlow<Resource<MovieDetails>>(Resource.Loading())
    val details: StateFlow<Resource<MovieDetails>> = _details

    private val _actors = MutableStateFlow<Resource<List<Actor>>>(Resource.Loading())
    val actors: StateFlow<Resource<List<Actor>>> = _actors

    fun loadDetails(movieId: Int) {
        viewModelScope.launch {
            _details.emit(
                value = repository.getDetails(movieId = movieId)
            )
        }
    }

    fun loadActorsList(movieId: Int) {
        viewModelScope.launch {
            _actors.emit(
                value = repository.getActors(movieId = movieId)
            )
        }
    }
}