package com.example.myfirstapp.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R

class MainFragment : Fragment() {

    //    Для создания вьюмодели используется делегат.
    //    Он создаст экземпляр вьюмодели только в момент первого обращения к ней.
    private val viewModel: MainViewModel by viewModels()

    //    Создаём адаптер
    private lateinit var adapter: MoviesAdapter
    private lateinit var recyclerMovies: RecyclerView
    private lateinit var buttonAdd: AppCompatButton
    private lateinit var buttonShowFavorites: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initRecyclerView()
        setUpShowFavoritesButton()
        setUpAddButton()
    }

    private fun initViews(view: View) {
        recyclerMovies = view.findViewById<RecyclerView>(R.id.recycler_movies)
        buttonAdd = view.findViewById<AppCompatButton>(R.id.button_add_movie)
        buttonShowFavorites = view.findViewById<AppCompatButton>(R.id.button_show_favorites)
    }

    //    Инициализируем RecyclerView
    private fun initRecyclerView() {
        adapter = MoviesAdapter(
            movies = listOf(),
            onViewMovieClick = this::onMovieClicked,
            onSetFavoriteClick = viewModel::setMovieFavorite
        )
        recyclerMovies.layoutManager = getLayoutManager()
        recyclerMovies.adapter = adapter

        val itemDecoration =
            DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL)
        val dividerDrawable =
            ContextCompat.getDrawable(requireActivity(), R.drawable.background_divider)

        dividerDrawable?.let {
            itemDecoration.setDrawable(dividerDrawable)
        }

        //  Устанавливаем ItemDecoration
        recyclerMovies.addItemDecoration(itemDecoration)

        //  Устанавливаем ItemAnimator
        recyclerMovies.itemAnimator = MovieItemAnimator()

        //  Устанавливаем наблюдатель списка фильмов из ViewModel
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.refreshMovies(movies.toMutableList())
        }
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        val orientation = resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager(requireActivity())
        } else {
            GridLayoutManager(requireActivity(), 2)
        }
    }

    private fun setUpAddButton() {
        buttonAdd.setOnClickListener {
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

    private fun setUpShowFavoritesButton() {
        buttonShowFavorites.setOnClickListener {
//            val intent = Intent(requireContext(), FavoriteMoviesActivity::class.java)
//            startActivity(intent)
        }
    }

    //    Обработка нажатия на кнопку
    private fun onMovieClicked(movie: Movie) {
//        val intent = Intent(requireContext(), MovieActivity::class.java)
//        intent.putExtra(MovieActivity.EXTRA_TITLE, movie.title)
//        intent.putExtra(MovieActivity.EXTRA_DESCRIPTION, movie.description)
//        intent.putExtra(MovieActivity.EXTRA_POSTER, movie.imageResId)
//        startActivityForResult(intent, 42)
    }
}