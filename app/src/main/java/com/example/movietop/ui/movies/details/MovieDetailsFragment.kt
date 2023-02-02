package com.example.movietop.ui.movies.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.movietop.R
import com.example.movietop.appComponent
import com.example.movietop.databinding.FragmentMovieDetailsBinding
import com.example.movietop.domain.movies.details.MovieDetails
import com.example.movietop.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val binding: FragmentMovieDetailsBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: dagger.Lazy<MovieDetailsViewModelFactory>

    private val detailsViewModel: MovieDetailsViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectDetailsFlow()

        arguments?.getInt(ARG_MOVIE_ID)?.let { id ->
            detailsViewModel.loadDetails(movieId = id)
        } ?: run { showErrorSnackBar(R.string.can_not_show_details) }
    }

    private fun collectDetailsFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                detailsViewModel.details.collect { state ->
                    checkState(state = state)
                }
            }
        }
    }

    private fun checkState(state: Resource<MovieDetails>) {
        when(state) {
            is Resource.Loading -> {}
            is Resource.Error -> {}
            is Resource.Success -> {
                showDetails(state.data)
            }
        }
    }

    private fun showDetails(details: MovieDetails) {
        with(binding) {
            ivCover.load(details.coverUrl)
            ivPoster.load(details.posterUrlPreview)

            tvDescription.text = details.description
        }
    }

    private fun showErrorSnackBar(textResource: Int) {
        Snackbar.make(binding.root, textResource, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }
}