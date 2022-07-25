package com.example.myfirstapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R

//  Адаптер для отображения списка
class MoviesAdapter(
    private var movies: List<Movie>,
    private val onViewMovieClick: (Movie) -> Unit,
    private val onSetFavoriteClick: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val viewHolder = MoviesViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onViewMovieClick, onSetFavoriteClick)
    }

    override fun getItemCount(): Int = movies.size

    fun refreshMovies(movies: List<Movie>){
        this.movies = movies
        notifyDataSetChanged()

    }

    //    ViewHolder
    class MoviesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(
            movie: Movie,
            onViewMovieClick: (Movie) -> Unit,
            onSetFavoriteClick: (Movie) -> Unit
        ) {
            val image = itemView.findViewById<ImageView>(R.id.image)
            val buttonViewInfo = itemView.findViewById<Button>(R.id.button_view_info)
            val buttonSetFavorite = itemView.findViewById<ImageView>(R.id.button_set_favorite)

            val favoriteImageResId = getFavoriteImageResId(movie.isFavorite)

            image.setImageResource(movie.imageResId)
            buttonViewInfo.apply {
                text = movie.title
                setOnClickListener { onViewMovieClick(movie) }
            }

            buttonSetFavorite.apply {
                setImageResource(favoriteImageResId)
                setOnClickListener { onSetFavoriteClick(movie) }
            }
        }

        private fun getFavoriteImageResId(isFavorite: Boolean) : Int {
            return if (isFavorite) R.drawable.ic_favorite
            else R.drawable.ic_not_favorite
        }
    }
}