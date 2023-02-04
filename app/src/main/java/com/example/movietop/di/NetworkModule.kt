package com.example.movietop.di

import com.example.movietop.BuildConfig
import com.example.movietop.data.movies.details.MovieDetailsApi
import com.example.movietop.data.movies.list.MoviesListApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addNetworkInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request.newBuilder().addHeader("X-API-KEY", BuildConfig.API_KEY).build()
                    chain.proceed(newRequest)
                }
                .build())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesListApi(retrofit: Retrofit): MoviesListApi {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideMovieDetailApi(retrofit: Retrofit): MovieDetailsApi {
        return retrofit.create()
    }
}