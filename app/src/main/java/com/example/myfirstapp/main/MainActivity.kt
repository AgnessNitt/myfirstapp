package com.example.myfirstapp.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.movie.MovieActivity
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource
import com.example.myfirstapp.favorite.FavoriteMoviesActivity
import com.example.myfirstapp.observer.MovieObserver


class MainActivity : AppCompatActivity(), MovieObserver {

    //    Создаём адаптер
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviesDataSource.createMovies(applicationContext)
        initRecyclerView()
        MoviesDataSource.addObserver(this)
        setOnShowFavoritesButtonClickListener()
        setUpAddButton()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 42) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getBooleanExtra(MovieActivity.RESULT_FAVORITE, false)
                Toast.makeText(this, "Результат $result", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMoviesChanged(movies: List<Movie>) {
        adapter.refreshMovies(movies.toMutableList())
    }

    override fun onDestroy() {
        super.onDestroy()
        MoviesDataSource.removeObserver(this)
    }

    //    Инициализируем RecyclerView
    private fun initRecyclerView() {
        adapter = MoviesAdapter(
            movies = MoviesDataSource.movies.toMutableList(),
            onViewMovieClick = this::onMovieClicked,
            onSetFavoriteClick = this::onSetFavoriteClicked
        )
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_movies)
        recyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.background_divider)

        dividerDrawable?.let {
            itemDecoration.setDrawable(dividerDrawable)
        }

        //        Устанавливаем ItemDecoration
        recyclerView.addItemDecoration(itemDecoration)

//        Устанавливаем ItemAnimator
        recyclerView.itemAnimator = MovieItemAnimator()
    }

    //    Обработка нажатия на кнопку
    private fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.EXTRA_TITLE, movie.title)
        intent.putExtra(MovieActivity.EXTRA_DESCRIPTION, movie.description)
        intent.putExtra(MovieActivity.EXTRA_POSTER, movie.imageResId)
        startActivityForResult(intent, 42)
    }

    //    Обработка нажатия на кнопку добавления в избранное
    private fun onSetFavoriteClicked(movie: Movie) {
        MoviesDataSource.setMovieFavorite(movie)
        adapter.refreshMovies(MoviesDataSource.movies.toMutableList())
    }

    private fun setOnShowFavoritesButtonClickListener() {
        val button = findViewById<AppCompatButton>(R.id.button_show_favorites)
        button.setOnClickListener {
            val intent = Intent(this, FavoriteMoviesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpAddButton() {
        val addButton = findViewById<AppCompatButton>(R.id.button_add_movie)
        addButton.setOnClickListener {
            adapter.addMovie(
                Movie(
                    title = getString(R.string.dogma),
                    description = getString(R.string.Dogma_text),
                    imageResId = R.drawable.dogma,
                    isFavorite = false
                )
            )
        }
    }
}

