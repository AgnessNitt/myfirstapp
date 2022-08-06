package com.example.myfirstapp.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.R

class MovieFragment : Fragment() {

    private lateinit var textDescription: TextView
    private lateinit var imagePoster: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
//        title = intent.getStringExtra(MovieActivity.EXTRA_TITLE) ?: error("No title provided")
//
//        val text = intent.getStringExtra(MovieActivity.EXTRA_DESCRIPTION) ?: error("No title provided")
//        description.text = text
//
//        val posterId = intent.getIntExtra(MovieActivity.EXTRA_POSTER, -1)
    }

    private fun initViews(view: View) {
        textDescription = view.findViewById<TextView>(R.id.description)
        imagePoster = view.findViewById<ImageView>(R.id.poster_d)
    }

    companion object {
        const val EXTRA_TITLE = "title"
        const val RESULT_FAVORITE = "favorite"
        const val EXTRA_DESCRIPTION = "description"
        const val EXTRA_POSTER = "poster"
    }
}