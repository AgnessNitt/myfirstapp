package com.example.myfirstapp.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.Movie
import com.example.myfirstapp.R

class FavoriteMovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMoviesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_favorite_movie,
            parent,
            false
        )
        return FavoriteMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movies.size

    fun refreshMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Movie = movies[position]

    class FavoriteMoviesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(movie: Movie) {
            val image = itemView.findViewById<ImageView>(R.id.image)
            val buttonViewInfo = itemView.findViewById<Button>(R.id.button_view_info)

            image.setImageResource(movie.imageResId)
            buttonViewInfo.text = movie.title
        }
    }
}