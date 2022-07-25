package com.example.myfirstapp.observer

import com.example.myfirstapp.Movie

interface MovieObserver {

    fun onMoviesChanged(movies: List<Movie>)
}