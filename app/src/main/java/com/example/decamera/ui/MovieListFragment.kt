package com.example.decamera.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.decamera.model.MovieResponse
import com.example.decamera.model.Result
import com.example.decamera.ui.viewmodel.MovieListViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {


    private lateinit var binding:FragmentMovieListBinding
    private val viewModel by viewModels<MovieListViewModel>()
    private var movieList : MutableLiveData<Result<MovieResponse>>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieListBinding.inflate(layoutInflater, container, false);

        //findNavController().navigate(R.id.action_movieListFragment_to_movieDetailsFragment, Bundle())
        fetchMovies()
        subscribeUI()


        return binding.root
    }


    private fun fetchMovies() {
        viewModel.makeGetMovieListApiCall()
    }

    private fun subscribeUI() {
        viewModel.movieList?.observe(viewLifecycleOwner) {
            Log.i("Midhun","it.status:"+it.status)
            when (it.status) {
                Result.Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(context, "SUCcESS :" + it.data, Toast.LENGTH_SHORT).show()
                }
                Result.Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(context, "ERROR :" + it.error.toString(), Toast.LENGTH_SHORT).show()
                }
                Result.Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }
        }
    }


}