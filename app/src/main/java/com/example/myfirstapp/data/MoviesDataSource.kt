package com.example.myfirstapp.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R
import com.example.myfirstapp.observer.MovieObservable

object MoviesDataSource : MovieObservable() {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>(listOf())
    val movies: LiveData<List<Movie>> = _movies

    override fun notifyObservers() {
//        observers.forEach {
//            it.onMoviesChanged(movies.value)
//        }
    }

    fun createMovies(context: Context) {
        val movies = listOf<Movie>(
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
        _movies.value = movies
    }

    fun setMovieFavorite(movie: Movie) {
        val copy = _movies.value?.toMutableList() ?: return
        val index = copy.indexOf(movie)
        val newMovie = movie.copy(isFavorite = !movie.isFavorite)
        copy[index] = newMovie
        _movies.value = copy
    }

    fun getFavoriteMovies(): List<Movie> =
        movies.value?.filter { movie -> movie.isFavorite } ?: listOf()

    fun deleteFromFavorites(movie: Movie) {
        val copy = _movies.value?.toMutableList() ?: return
        val index = copy.indexOf(movie)
        val newMovie = movie.copy(isFavorite = false)
        copy[index] = newMovie
        _movies.value = copy
    }
}