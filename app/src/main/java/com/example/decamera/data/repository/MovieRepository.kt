package com.example.decamera.data.repository

import android.util.Log
import com.example.decamera.data.RemoteDataSource
import com.example.decamera.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import com.example.decamera.model.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    suspend fun fetchTrendingMovies() : Flow<Result<MovieResponse>> {
        return flow {
            emit(Result.loading())
            val result = remoteDataSource.fetchMovies()
            emit(result)
        }

    }

}