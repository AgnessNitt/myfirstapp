package com.example.myfirstapp.favorite

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource
import com.example.myfirstapp.main.MainViewModel

class FavoriteMoviesActivity : AppCompatActivity() {

    //    Для создания вьюмодели используется делегат.
    //    Он создаст экземпляр вьюмодели только в момент первого обращения к ней.
    private val viewModel: FavoriteMoviesViewModel by viewModels()

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
        recyclerFavoriteMovies.layoutManager = getLayoutManager()

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
                    viewModel.deleteFromFavorites(movie)
                }
            }
        )
        itemTouchHelper.attachToRecyclerView(recyclerFavoriteMovies)
        viewModel.favoriteMovies.observe(this) { adapter.refreshMovies(it) }
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        val orientation = resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager(this)
        } else {
            GridLayoutManager(this, 2)
        }
    }
}