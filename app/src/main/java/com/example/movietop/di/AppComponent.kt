package com.example.movietop.di

import com.example.movietop.ui.movies.details.MovieDetailsFragment
import com.example.movietop.ui.movies.list.MoviesListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DispatchersModule::class, BindsModule::class])
interface AppComponent {
    fun inject(fragment: MoviesListFragment)

    fun inject(fragment: MovieDetailsFragment)
}