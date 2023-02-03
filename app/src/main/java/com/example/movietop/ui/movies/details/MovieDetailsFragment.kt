package com.example.movietop.ui.movies.details

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectDetailsFlow()

        arguments?.getInt(ARG_MOVIE_ID)?.let { id ->
            detailsViewModel.loadDetails(movieId = id)
        } ?: run { showErrorSnackBar(R.string.can_not_show_details) }
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onDetach()
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
            tvYearValue.text = details.year
            tvLength.text = details.filmLength
            tvGenres.text = details.genres

            rating.pbRating.progress = details.ratingForProgress
            rating.tvRatingValue.text = details.ratingForText

            tvDescription.text = details.description
            tvName.text = details.name
        }
    }

    private fun showErrorSnackBar(textResource: Int) {
        Snackbar.make(binding.root, textResource, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }
}