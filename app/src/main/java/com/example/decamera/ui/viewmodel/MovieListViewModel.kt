package com.example.decamera.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.decamera.data.repository.MovieRepository
import com.example.decamera.model.MovieResponse
import com.example.decamera.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movieList = MutableLiveData<Result<MovieResponse>>()
    var movieList = _movieList

     fun makeGetMovieListApiCall() {
        viewModelScope.launch {
           movieRepository.fetchTrendingMovies().collect {
               _movieList?.value = it
           }
        }
    }
}
