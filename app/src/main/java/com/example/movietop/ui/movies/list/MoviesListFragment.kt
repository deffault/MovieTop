package com.example.movietop.ui.movies.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.movietop.R
import com.example.movietop.appComponent
import com.example.movietop.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    private val binding: FragmentMoviesListBinding by viewBinding()
    private val adapter = MoviesListAdapter()

    @Inject
    lateinit var viewModelFactory: dagger.Lazy<MoviesListViewModelFactory>

    private val viewModel: MoviesListViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovies.apply {
            adapter = this@MoviesListFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), GRID_COLUMN_COUNT)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.filmsFlow.collect {
                    adapter.submitData(it)
                }
            }
        }
    }

    companion object {
        private const val GRID_COLUMN_COUNT = 2
    }
}