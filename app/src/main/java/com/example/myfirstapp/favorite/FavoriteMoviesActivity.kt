package com.example.myfirstapp.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource

class FavoriteMoviesActivity : AppCompatActivity() {

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var moviesAdapter: FavoriteMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewMovies = findViewById(R.id.recycler_favorite_movies)
        moviesAdapter = FavoriteMovieAdapter(movies = MoviesDataSource.getFavoriteMovies())

        recyclerViewMovies.adapter = moviesAdapter

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
                    val movie = moviesAdapter.getItem(viewHolder.adapterPosition)
                    MoviesDataSource.deleteFromFavorites(movie)
                    moviesAdapter.refreshMovies(MoviesDataSource.getFavoriteMovies())
                }
            }
        )
        itemTouchHelper.attachToRecyclerView(recyclerViewMovies)
    }
}