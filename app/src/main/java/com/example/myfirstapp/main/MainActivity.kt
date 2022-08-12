package com.example.myfirstapp.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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

    private val REQUEST_CODE = 42

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var buttonShowFavorites: Button
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MoviesDataSource.createMovies(applicationContext)
        initRecyclerView()
        MoviesDataSource.addObserver(this)
        setOnShowFavoritesButtonClickListener()
    }
    override fun onDestroy() {
        super.onDestroy()
        MoviesDataSource.removeObserver(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                val result = data.getBooleanExtra(MovieActivity.KEY_RESULT_FAVORITE, false)
                Toast.makeText(
                    this,
                    getString(R.string.result_pattern, result.toString()),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun onMoviesChanged(movies: List<Movie>) = moviesAdapter.refreshMovies(movies)

    override fun onBackPressed() = showExitDialog()

    private fun initRecyclerView() {
        recyclerViewMovies = findViewById(R.id.recycler_movies)
        moviesAdapter = MoviesAdapter(
            movies = MoviesDataSource.movies,
            onViewMovieClick = this::onMovieClicked,
            onSetFavoriteClick = this::onSetFavoriteClicked
        )
        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.background_divider)

        dividerDrawable?.let {
            itemDecoration.setDrawable(dividerDrawable)
        }

        recyclerViewMovies.apply {
            adapter = this@MainActivity.moviesAdapter
            addItemDecoration(itemDecoration)
        }
    }

    private fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, MovieActivity::class.java)
        with(intent) {
            putExtra(MovieActivity.KEY_TITLE, movie.title)
            putExtra(MovieActivity.KEY_DESCRIPTION, movie.description)
            putExtra(MovieActivity.KEY_POSTER_ID, movie.imageResId)
            startActivityForResult(this, REQUEST_CODE)
        }
    }

    private fun onSetFavoriteClicked(movie: Movie) {
        MoviesDataSource.setMovieFavorite(movie)
        moviesAdapter.refreshMovies(MoviesDataSource.movies)
    }

    private fun setOnShowFavoritesButtonClickListener() {
        buttonShowFavorites = findViewById<AppCompatButton>(R.id.button_show_favorites)
        buttonShowFavorites.setOnClickListener {
            val intent = Intent(this, FavoriteMoviesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setMessage(R.string.exit_dialog_message)
            .setTitle(R.string.exit_dialog_title)
            .setPositiveButton(R.string.button_yes) { dialog, id ->
                this.finish()
            }
            .setNegativeButton(R.string.button_no) { dialog, id ->
                dialog.cancel()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
}