package com.example.myfirstapp.data

import android.content.Context
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R

object MoviesDataSource {

    private var _movies: MutableList<Movie> = mutableListOf()
    val movies: List<Movie>
        get() = _movies

    //    Создаём список фильмов
    fun createMovies(context: Context) {
        val movies = mutableListOf<Movie>(
            Movie(
                title = context.getString(R.string.dogma),
                description = context.getString(R.string.Dogma_text),
                imageResId = R.drawable.dogma,
                isFavorite = false
            ),
            Movie(
                title = context.getString(R.string.mne_ne_bolno),
                description = context.getString(R.string.MNB_text),
                imageResId = R.drawable.mnenebolno,
                isFavorite = false
            ),
            Movie(
                title = context.getString(R.string.plesantville),
                description = context.getString(R.string.Pl_text),
                imageResId = R.drawable.pleasantville,
                isFavorite = false
            ),
            Movie(
                title = context.getString(R.string.coven),
                description = context.getString(R.string.Coven_text),
                imageResId = R.drawable.coven,
                isFavorite = false
            ),
            Movie(
                title = context.getString(R.string.stariki),
                description = context.getString(R.string.St_text),
                imageResId = R.drawable.stariki,
                isFavorite = false
            ),
            Movie(
                title = context.getString(R.string.truman),
                description = context.getString(R.string.TS_text),
                imageResId = R.drawable.truman,
                isFavorite = false
            )
        )
        _movies = movies
    }

    fun setMovieFavorite(movie: Movie) {
        val index = _movies.indexOf(movie)
        val newMovie = movie.copy(isFavorite = !movie.isFavorite)
        _movies[index] = newMovie
    }

    fun getFavoriteMovies(): List<Movie> {
        val favoriteMovies = movies.filter { movie -> movie.isFavorite }
        return favoriteMovies
    }
}