package com.example.myfirstapp.favorite

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.R
import com.example.myfirstapp.data.MoviesDataSource


class FavoriteFragment : Fragment() {

    //    Для создания вьюмодели используется делегат.
    //    Он создаст экземпляр вьюмодели только в момент первого обращения к ней.
    private val viewModel: FavoriteMoviesViewModel by viewModels()

    //    Создаём адаптер
    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var recyclerMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initRecyclerView()
    }

    private fun initViews(view: View) {
        recyclerMovies = view.findViewById<RecyclerView>(R.id.recycler_favorite_movies)
    }

    //    Инициализируем RecyclerView
    private fun initRecyclerView() {
        adapter = FavoriteMovieAdapter(movies = MoviesDataSource.getFavoriteMovies())
        recyclerMovies.adapter = adapter
        recyclerMovies.layoutManager = getLayoutManager()

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
        itemTouchHelper.attachToRecyclerView(recyclerMovies)
        viewModel.favoriteMovies.observe(viewLifecycleOwner) { adapter.refreshMovies(it) }
    }

    private fun getLayoutManager(): RecyclerView.LayoutManager {
        val orientation = resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayoutManager(requireActivity())
        } else {
            GridLayoutManager(requireActivity(), 2)
        }
    }

}