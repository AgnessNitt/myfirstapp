package com.example.myfirstapp.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource
import com.example.myfirstapp.main.MoviesAdapter

class FavoriteMoviesActivity : AppCompatActivity() {

    //    Создаём адаптер
    private lateinit var adapter: FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)
        initRecyclerView()
    }

    //    Инициализируем RecyclerView
    private fun initRecyclerView() {
        adapter = FavoriteMovieAdapter(movies = MoviesDataSource.getFavoriteMovies())
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_favorite_movies)
        recyclerView.adapter = adapter
    }
}