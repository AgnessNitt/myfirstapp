package com.example.myfirstapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapp.Movie
import com.example.myfirstapp.data.MoviesDataSource

class MainViewModel : ViewModel() {
    val movies: LiveData<List<Movie>> = MoviesDataSource.movies

    fun onSetFavoriteClicked(movie: Movie) = MoviesDataSource.setMovieFavorite(movie)
    fun onMovieClicked(movie: Movie) {

    }

    fun onShowFavoritesClicked() {

    }
}