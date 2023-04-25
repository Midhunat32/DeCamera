package com.example.decamera.network

import com.example.decamera.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

//https://api.themoviedb.org/3/movie/550?api_key=72fb2b6f577a855c93345da1045c6c6e
  @GET("/3/trending/movie/week")
  suspend fun getPopularMovies() : Response<MovieResponse>


}