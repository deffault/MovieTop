package com.example.movietop.ui.movies.details

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.movietop.R
import com.example.movietop.appComponent
import com.example.movietop.databinding.FragmentMovieDetailsBinding
import com.example.movietop.domain.movies.details.MovieDetails
import com.example.movietop.domain.movies.details.actors.Actor
import com.example.movietop.ui.movies.details.actors.ActorsAdapter
import com.example.movietop.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {
    private val binding: FragmentMovieDetailsBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: dagger.Lazy<MovieDetailsViewModelFactory>

    private val detailsViewModel: MovieDetailsViewModel by viewModels { viewModelFactory.get() }

    private val actorsAdapter = ActorsAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActorsRecycler()
        collectDetailsFlow()
        collectActorsFlow()

        arguments?.getInt(ARG_MOVIE_ID)?.let { id ->
            detailsViewModel.loadDetails(movieId = id)
            detailsViewModel.loadActorsList(movieId = id)
        } ?: run { showErrorSnackBar(getString(R.string.can_not_show_details)) }
    }

    override fun onDetach() {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onDetach()
    }

    private fun initActorsRecycler() {
        binding.rvActors.apply {
            adapter = actorsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun collectDetailsFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                detailsViewModel.details.collect { state ->
                    checkDetailsState(state = state)
                }
            }
        }
    }

    private fun collectActorsFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                detailsViewModel.actors.collect { state ->
                    checkActorState(state = state)
                }
            }
        }
    }

    private fun checkDetailsState(state: Resource<MovieDetails>) {
        when(state) {
            is Resource.Loading -> {
                with(binding) {
                    rvActors.isVisible = false
                    detailProgress.isVisible = true
                }
            }
            is Resource.Error -> {
                with(binding) {
                    rvActors.isVisible = false
                    detailProgress.isVisible = false
                }
                showErrorSnackBar(state.message)
            }
            is Resource.Success -> {
                with(binding) {
                    rvActors.isVisible = true
                    detailProgress.isVisible = false
                }
                showDetails(state.data)
            }
        }
    }

    private fun checkActorState(state: Resource<List<Actor>>) {
        when(state) {
            is Resource.Loading -> {
                with(binding) {
                    contentGroup.isVisible = false
                    detailProgress.isVisible = true
                }
            }
            is Resource.Error -> {
                with(binding) {
                    contentGroup.isVisible = false
                    detailProgress.isVisible = false
                }
                showErrorSnackBar(state.message)
            }
            is Resource.Success -> {
                with(binding) {
                    contentGroup.isVisible = true
                    detailProgress.isVisible = false
                }
                showActors(actors = state.data)
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

    private fun showActors(actors: List<Actor>) {
        actorsAdapter.submitList(actors)
    }

    private fun showErrorSnackBar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val ARG_MOVIE_ID = "ARG_MOVIE_ID"
    }
}