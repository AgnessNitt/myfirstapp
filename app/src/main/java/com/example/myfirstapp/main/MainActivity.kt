package com.example.myfirstapp.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.movie.MovieActivity
import com.example.myfirstapp.R
import com.example.myfirstapp.favorite.FavoriteMoviesActivity


class MainActivity : AppCompatActivity() {
    //    Для создания вьюмодели используется делегат.
    //    Он создаст экземпляр вьюмодели только в момент первого обращения к ней.
    private val viewModel: MainViewModel by viewModels()

    //    Создаём адаптер
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        setUpShowFavoritesButton()
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

    override fun onBackPressed() = showExitDialog()

    //    Инициализируем RecyclerView
    private fun initRecyclerView() {
        adapter = MoviesAdapter(
            movies = listOf(),
            onViewMovieClick = this::onMovieClicked,
            onSetFavoriteClick = viewModel::setMovieFavorite
        )
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_movies)
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.adapter = adapter

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(this, R.drawable.background_divider)

        dividerDrawable?.let {
            itemDecoration.setDrawable(dividerDrawable)
        }

        //  Устанавливаем ItemDecoration
        recyclerView.addItemDecoration(itemDecoration)

        //  Устанавливаем ItemAnimator
        recyclerView.itemAnimator = MovieItemAnimator()

        //  Устанавливаем наблюдатель списка фильмов из ViewModel
        viewModel.movies.observe(this) { movies -> adapter.refreshMovies(movies.toMutableList()) }
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        val orientation = resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager(this)
        } else {
            GridLayoutManager(this, 2)
        }
    }

    //    Обработка нажатия на кнопку
    private fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.EXTRA_TITLE, movie.title)
        intent.putExtra(MovieActivity.EXTRA_DESCRIPTION, movie.description)
        intent.putExtra(MovieActivity.EXTRA_POSTER, movie.imageResId)
        startActivityForResult(intent, 42)
    }

    private fun setUpShowFavoritesButton() {
        val button = findViewById<AppCompatButton>(R.id.button_show_favorites)
        button.setOnClickListener {
            val intent = Intent(this, FavoriteMoviesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpAddButton() {
        val addButton = findViewById<AppCompatButton>(R.id.button_add_movie)
        addButton.setOnClickListener {
            viewModel.addMovie(
                Movie(
                    title = R.string.dogma,
                    description = R.string.Dogma_text,
                    imageResId = R.drawable.dogma,
                    isFavorite = false
                )
            )
        }
    }

    private fun showExitDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.exit_dialog_message)
            .setTitle(R.string.exit_dialog_title)
            .setPositiveButton(R.string.button_yes) { dialog, id ->
                dialog.dismiss()
                this.finish()
            }
            .setNegativeButton(R.string.button_no) { dialog, id ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

