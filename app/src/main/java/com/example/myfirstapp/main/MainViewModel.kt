package com.example.myfirstapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource

class MainViewModel : ViewModel() {

    val movies: LiveData<List<Movie>> = MoviesDataSource.movies

    fun setMovieFavorite(movie: Movie) = MoviesDataSource.setMovieFavorite(movie)

    fun addMovie(movie: Movie) = MoviesDataSource.addMovie(movie)
}