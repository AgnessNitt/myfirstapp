package com.example.myfirstapp.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource

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
        val recyclerFavoriteMovies = findViewById<RecyclerView>(R.id.recycler_favorite_movies)
        recyclerFavoriteMovies.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val movie = adapter.getItem(viewHolder.adapterPosition)
                    MoviesDataSource.deleteFromFavorites(movie)
                    adapter.refreshMovies(MoviesDataSource.getFavoriteMovies())
                }
            }
        )
        itemTouchHelper.attachToRecyclerView(recyclerFavoriteMovies)
    }
}