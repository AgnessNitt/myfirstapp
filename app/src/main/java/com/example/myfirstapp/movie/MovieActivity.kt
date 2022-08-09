package com.example.myfirstapp.movie

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.R


class MovieActivity : AppCompatActivity() {

    private lateinit var textViewDescription: TextView
    private lateinit var imageViewPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initViews()
        setTitle()
        setDescription()
        setPoster()
        setResult()
    }

    private fun initViews() {
        textViewDescription = findViewById(R.id.description)
        imageViewPoster = findViewById(R.id.poster_d)
    }

    private fun setTitle() {
        intent.getStringExtra(KEY_TITLE)?.let {
            title = it
        }
    }

    private fun setDescription() {
        intent.getStringExtra(KEY_DESCRIPTION)?.let {
            textViewDescription.text = it
        }
    }

    private fun setPoster() {
        val posterId = intent.getIntExtra(KEY_POSTER_ID, DEFAULT_POSTER_ID)
        if (posterId != DEFAULT_POSTER_ID) {
            imageViewPoster.setImageResource(posterId)
        }
    }

    private fun setResult() {
        val result = Intent()
        result.putExtra(KEY_RESULT_FAVORITE, true)
        setResult(RESULT_OK, result)
    }

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
        const val KEY_POSTER_ID = "poster"
        const val KEY_RESULT_FAVORITE = "favorite"

        private const val DEFAULT_POSTER_ID = -1
    }
}