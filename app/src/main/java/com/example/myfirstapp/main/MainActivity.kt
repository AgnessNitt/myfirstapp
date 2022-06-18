package com.example.myfirstapp.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.movie.MovieActivity
import com.example.myfirstapp.R


class MainActivity : AppCompatActivity() {

    //    Создаём адаптер
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = MoviesAdapter(movies = getMovies(), onClick = this::onMovieClicked)
        initRecyclerView()
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

    //    Инициализируем RecyclerView
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_movies)
        recyclerView.adapter = adapter
    }

    //    Создаём список фильмов
    private fun getMovies(): List<Movie> {
        val movies = listOf<Movie>(
            Movie(
                title = "Догма",
                description = this.getString(R.string.Dogma_text),
                imageResId = R.drawable.dogma
            ),
            Movie(
                title = "Мне не больно",
                description = this.getString(R.string.MNB_text),
                imageResId = R.drawable.mnenebolno
            ),
            Movie(
                title = "Плезантвиль",
                description = this.getString(R.string.Pl_text),
                imageResId = R.drawable.pleasantville
            ),
            Movie(
                title = "Ковен",
                description = this.getString(R.string.Coven_text),
                imageResId = R.drawable.coven
            ),
            Movie(
                title = "В бой идут одни старики",
                description = this.getString(R.string.St_text),
                imageResId = R.drawable.stariki
            ),
            Movie(
                title = "Шоу Трумана",
                description = this.getString(R.string.TS_text),
                imageResId = R.drawable.truman
            )
        )
        return movies
    }

    //    Обработка нажатия на кнопку
    private fun onMovieClicked(movie: Movie) {
        val intent = Intent(this, MovieActivity::class.java)
        intent.putExtra(MovieActivity.EXTRA_TITLE, movie.title)
        intent.putExtra(MovieActivity.EXTRA_DESCRIPTION, movie.description)
        intent.putExtra(MovieActivity.EXTRA_POSTER, movie.imageResId)
        startActivityForResult(intent, 42)
    }
}

