package com.example.movietop.data.movies.details

import com.example.movietop.domain.movies.details.MovieDetails
import com.example.movietop.domain.movies.details.MovieDetailsRepository
import com.example.movietop.domain.movies.details.actors.Actor
import com.example.movietop.utils.Resource
import com.example.movietop.utils.mappers.toActor
import com.example.movietop.utils.mappers.toMovieDetails
import com.example.movietop.utils.providers.dispatchers.DispatcherProvider
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val api: MovieDetailsApi,
    private val dispatcherProvider: DispatcherProvider
) : MovieDetailsRepository {
    override suspend fun getDetails(movieId: Int): Resource<MovieDetails> = withContext(dispatcherProvider.io()) {
        return@withContext try {
            val details = api.getMovieDetails(id = movieId)
            Resource.Success(data = details.toMovieDetails())
        } catch (e: HttpException) {
            Resource.Error(message = e.message())
        } catch (e: IOException) {
            val message = e.message ?: "Произошла ошибка при загрузке информации о фильме"
            Resource.Error(message = message)
        }
    }

    override suspend fun getActors(movieId: Int): Resource<List<Actor>> = withContext(dispatcherProvider.io()) {
        return@withContext try {
            val actors = api.getActorsList(id = movieId)
            Resource.Success(data = actors.map { dto -> dto.toActor() })
        } catch (e: HttpException) {
            Resource.Error(message = e.message())
        } catch (e: IOException) {
            val message = e.message ?: "Произошла ошибка при загрузке списка актеров"
            Resource.Error(message = message)
        }
    }
}