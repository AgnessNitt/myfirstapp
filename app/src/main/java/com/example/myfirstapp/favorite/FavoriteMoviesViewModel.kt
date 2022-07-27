package com.example.myfirstapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.myfirstapp.Movie
import com.example.myfirstapp.data.MoviesDataSource

class FavoriteMoviesViewModel : ViewModel() {

    val favoriteMovies = MoviesDataSource.movies.map { movies ->
        movies.filter { it.isFavorite }
    }

    fun deleteFromFavorites(movie: Movie) = MoviesDataSource.deleteFromFavorites(movie)
}