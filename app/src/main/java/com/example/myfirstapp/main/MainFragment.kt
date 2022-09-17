package com.example.myfirstapp.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource
import com.example.myfirstapp.favorite.FavoriteMoviesActivity
import com.example.myfirstapp.movie.MovieActivity
import com.example.myfirstapp.observer.MovieObserver


class MainFragment : Fragment(), MovieObserver {

    private lateinit var recyclerViewMovies: RecyclerView
    private lateinit var buttonShowFavorites: Button
    private lateinit var moviesAdapter: MoviesAdapter

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MoviesDataSource.createMovies(requireContext())
        initRecyclerView(view)
        MoviesDataSource.addObserver(this)
        setOnShowFavoritesButtonClickListener(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        MoviesDataSource.removeObserver(this)
    }

    override fun onMoviesChanged(movies: List<Movie>) = moviesAdapter.refreshMovies(movies)

    private fun initRecyclerView(view: View) {
        recyclerViewMovies = view.findViewById(R.id.recycler_movies)
        moviesAdapter = MoviesAdapter(
            movies = viewModel.movies.value ?: listOf(),
            onViewMovieClick = viewModel::onMovieClicked,
            onSetFavoriteClick = viewModel::onSetFavoriteClicked
        )

        val itemDecoration = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ContextCompat.getDrawable(view.context, R.drawable.background_divider)

        dividerDrawable?.let {
            itemDecoration.setDrawable(dividerDrawable)
        }

        recyclerViewMovies.apply {
            adapter = this@MainFragment.moviesAdapter
            addItemDecoration(itemDecoration)
            itemAnimator = MovieItemAnimator()
        }

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            moviesAdapter.refreshMovies(movies)
        }
    }

    private fun onMovieClicked(movie: Movie) {
        //     navigate to MovieFragment
    }

    private fun setOnShowFavoritesButtonClickListener(view: View) {
        buttonShowFavorites = view.findViewById<AppCompatButton>(R.id.button_show_favorites)
        buttonShowFavorites.setOnClickListener {
            viewModel.onShowFavoritesClicked()
        }
    }
}