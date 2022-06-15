package com.example.myfirstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class MoviesAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit,
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val viewHolder = MoviesViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onClick)
    }

    override fun getItemCount(): Int = movies.size


    class MoviesViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(movie: Movie, onClick: (Movie) -> Unit) {
            val image = itemView.findViewById<ImageView>(R.id.image)
            val buttonViewInfo = itemView.findViewById<Button>(R.id.button_view_info)

            image.setImageResource(movie.imageResId)
            buttonViewInfo.text = movie.title
            buttonViewInfo.setOnClickListener { onClick(movie) }
        }
    }
}