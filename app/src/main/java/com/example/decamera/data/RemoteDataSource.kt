package com.example.decamera.data

import android.util.Log
import com.example.decamera.model.Error
import com.example.decamera.model.MovieResponse
import retrofit2.Retrofit
import javax.inject.Inject
import com.example.decamera.model.Result
import com.example.decamera.network.NetworkApi
import retrofit2.Response

class RemoteDataSource @Inject constructor(private val retrofit: Retrofit){

    suspend fun fetchMovies() : Result<MovieResponse> {
       val service =  retrofit.create(NetworkApi::class.java)
        return getResponse(request = {service.getPopularMovies()})
    }

     private suspend fun <T> getResponse(request: suspend()-> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body()!!)
            } else{
                Result.toString()
                return Result.error(result.errorBody().toString(), Error(result.code()))
            }
        } catch (e : Throwable) {
            Result.toString()
            return Result.error(e.toString(), null)
        }
    }
}