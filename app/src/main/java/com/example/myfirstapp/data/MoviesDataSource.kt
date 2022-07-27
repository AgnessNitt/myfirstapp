package com.example.myfirstapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R
import com.example.myfirstapp.observer.MovieObservable

object MoviesDataSource {

    private var _movies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>(listOf())
    val movies: LiveData<List<Movie>> = _movies

    init {
        createMovies()
    }

    fun setMovieFavorite(movie: Movie) {
        val list = movies.value?.toMutableList() ?: return
        val index = list.indexOf(movie)
        val newMovie = movie.copy(isFavorite = !movie.isFavorite)
        list[index] = newMovie
        _movies.value = list
    }

    fun addMovie(movie: Movie) {
        val list = movies.value?.toMutableList() ?: return
        if (list.size >= 2) list.add(1, movie)
        _movies.value = list
    }

    fun getFavoriteMovies(): List<Movie> {
        val favoriteMovies = movies.value?.filter { movie -> movie.isFavorite } ?: return listOf()
        return favoriteMovies
    }

    fun deleteFromFavorites(movie: Movie) {
        val list = movies.value?.toMutableList() ?: return
        val index = list.indexOf(movie)
        val newMovie = movie.copy(isFavorite = false)
        list[index] = newMovie
        _movies.value = list
    }

    //    Создаём список фильмов
    private fun createMovies() {
        val movies = mutableListOf<Movie>(
            Movie(
                title = R.string.dogma,
                description = R.string.Dogma_text,
                imageResId = R.drawable.dogma,
                isFavorite = false
            ),
            Movie(
                title = R.string.mne_ne_bolno,
                description = R.string.MNB_text,
                imageResId = R.drawable.mnenebolno,
                isFavorite = false
            ),
            Movie(
                title = R.string.plesantville,
                description = R.string.Pl_text,
                imageResId = R.drawable.pleasantville,
                isFavorite = false
            ),
            Movie(
                title = R.string.coven,
                description = R.string.Coven_text,
                imageResId = R.drawable.coven,
                isFavorite = false
            ),
            Movie(
                title = R.string.stariki,
                description = R.string.St_text,
                imageResId = R.drawable.stariki,
                isFavorite = false
            ),
            Movie(
                title = R.string.truman,
                description = R.string.TS_text,
                imageResId = R.drawable.truman,
                isFavorite = false
            )
        )
        _movies.value = movies
    }
}